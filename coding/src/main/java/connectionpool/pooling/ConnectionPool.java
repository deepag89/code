package connectionpool.pooling;

import connectionpool.connection.Connection;
import connectionpool.connection.ConnectionFactory;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

public class ConnectionPool {
  private List<Connection> freeConnections;
  private int size;
  private final static int DEFAULT_POOL_SIZE = 10;
  private int MAX_POOL_SIZE = 100;
  private ConnectionFactory connectionFactory;
  private String url;

  ConnectionPool(ConnectionFactory connectionFactory, String url) {
    this(DEFAULT_POOL_SIZE, connectionFactory, url);
  }

  ConnectionPool(int size, ConnectionFactory connectionFactory, String url) {
    if (size > MAX_POOL_SIZE) {
      size = MAX_POOL_SIZE;
    }
    this.size = size;
    this.connectionFactory = connectionFactory;
    this.url = url;
    freeConnections = instantiateConnections(this.size);
  }

  private LinkedList<Connection> instantiateConnections(int n) {
    LinkedList<Connection> connections = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      // The real connections will be provided by the connection factory
      // Connection pool code is agnostic to the implementation
      connections.add(connectionFactory.createConnection(url));
    }
    return connections;
  }

  /**
   * Closes only the unused connections. The used connections aren't freed. The used connections will be shut down by GC
   * when there are no more references to the ConnectionWrapper, and hence the real connections will be GCed.
   */
  public void shutdown() {
    System.out.println("Connection Pool shutdown. Releasing unused connections");
    freeConnections.forEach(Connection::close);
    freeConnections = Collections.emptyList();
  }

  protected void finalize() {
    shutdown();
  }

  public Connection borrowConnection() {
    if (CollectionUtils.isEmpty(freeConnections)) {
      return null;
    }
    Connection realConn = removeConnectionFromPool();
    return new ConnectionWrapper(realConn, this);
  }

  public void returnConnection(Connection conn) {
    if (!(conn instanceof ConnectionWrapper)) {
      throw new IllegalArgumentException("What connection type are you trying to return?");
    }
    ConnectionWrapper connectionWrapper = (ConnectionWrapper) conn;
    if (!connectionWrapper.isCreatedBy(this)) {
      throw new IllegalArgumentException("You can't return me something you didn't borrow from me");
    }
    addConnectionToPool(connectionWrapper.realConn);
  }

  private Connection removeConnectionFromPool() {
    return freeConnections.remove(0);
  }

  private void addConnectionToPool(Connection realConn) {
    freeConnections.add(realConn);
  }

  public int getCurrentFreePoolSize() {
    return freeConnections.size();
  }

  /**
   * A wrapper over connections. It's created for multiple reasons:
   *
   * 1. When a close() in invoked on a connection, we don't want to close the connection. Instead, we want it to
   * return to the free connection pool. We proxy the ConnectionWrapper.close() method to achieve this.
   *
   * 2. Once a connection is released by client, he should no longer be able to read from it. As we don't want to
   * close the connection, the client can still call a conn.read() on it. We need to maintain a state
   * (isOpenForClient) for the client indicating the connection can no longer be used by the old client.
   *
   * 3. If a connection is not explicitly closed by the client and the client exits, we have no way to know that
   * and the connection can't be added back to the pool. We address this problem using ConnectionWrapper.finalize()
   *
   * A downside of using the wrapper is that we have to implement every method the real connection implements.
   *
   * See https://stackoverflow.com/questions/6595310/implementing-connection-pooling-java
   */
  class ConnectionWrapper implements Connection {
    private Connection realConn;
    private boolean isOpenForClient;
    private ConnectionPool poolReference;

    ConnectionWrapper(Connection realConn, ConnectionPool poolReference) {
      this.realConn = realConn;
      this.isOpenForClient = true;
      this.poolReference = poolReference;
    }

    @Override public String read() {
      if (!isOpenForClient) {
        throw new IllegalStateException("You had closed the connection! Remember?");
      }
      return realConn.read();
    }

    @Override public void close() {
      if (isOpenForClient) {
        isOpenForClient = false;
        addConnectionToPool(realConn);
      }
    }

    boolean isCreatedBy(ConnectionPool poolReference) {
      return poolReference == this.poolReference;
    }

    protected void finalize() {
      this.close();
    }
  }
}
