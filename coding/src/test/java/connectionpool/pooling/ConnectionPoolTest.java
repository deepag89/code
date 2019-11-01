package connectionpool.pooling;

import static org.junit.jupiter.api.Assertions.*;

import connectionpool.connection.Connection;
import connectionpool.connection.ConnectionFactory;
import connectionpool.mysql.MySQLConnectionFactory;
import connectionpool.postgresql.PostgreSQLConnectionFactory;
import org.junit.jupiter.api.Test;

class ConnectionPoolTest {
  private final String url1 = "localhost://jdbc:mydb";
  private final String url2 = "127.01.13.4://jdc:otherurl";
  private final String mySQLData = "MySQL data";
  private final String postgreSQLData = "PostgreSQL data";

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
  }

  @org.junit.jupiter.api.AfterEach
  void tearDown() {
    Runtime.getRuntime().gc();
  }

  @Test
  void testConnectionPoolThrowsExceptionAfterClosingConnection() {
    ConnectionFactory connectionFactory = new MySQLConnectionFactory();
    ConnectionPool connectionPool = new ConnectionPool(5, connectionFactory, url1);
    Connection conn = connectionPool.borrowConnection();
    assertEquals(conn.read(), mySQLData);
    conn.close();
    assertThrows(IllegalStateException.class, conn::read);
  }

  @Test
  void testConnectionPoolMaxSize() {
    ConnectionFactory connectionFactory = new MySQLConnectionFactory();
    ConnectionPool connectionPool = new ConnectionPool(2, connectionFactory, url1);
    Connection conn1 = connectionPool.borrowConnection();
    Connection conn2 = connectionPool.borrowConnection();
    assertNotNull(conn1);
    assertNotNull(conn2);
    assertNull(connectionPool.borrowConnection());
  }

  @Test
  void testConnectionPoolReuseConnections() {
    ConnectionFactory connectionFactory = new MySQLConnectionFactory();
    ConnectionPool connectionPool = new ConnectionPool(2, connectionFactory, url1);
    Connection conn1 = connectionPool.borrowConnection();
    Connection conn2 = connectionPool.borrowConnection();
    Connection conn3 = connectionPool.borrowConnection();
    assertNull(conn3);
    conn2.close();
    conn3 = connectionPool.borrowConnection();
    assertEquals(conn3.read(), mySQLData);
    assertThrows(IllegalStateException.class, conn2::read);
    assertNull(connectionPool.borrowConnection());
  }

  @Test
  void testSameDBMultipleConnectionPools() {
    ConnectionFactory connectionFactory = new MySQLConnectionFactory();
    ConnectionPool connectionPool1 = new ConnectionPool(5, connectionFactory, url1);
    ConnectionPool connectionPool2 = new ConnectionPool(5, connectionFactory, url2);
    assertNotEquals(connectionPool1, connectionPool2);

    Connection poolconn1 = connectionPool1.borrowConnection();
    Connection poolconn2 = connectionPool1.borrowConnection();
    assertEquals(connectionPool1.getCurrentFreePoolSize(), 5-2);

    connectionPool1.returnConnection(poolconn2);
    assertEquals(connectionPool1.getCurrentFreePoolSize(), 5-1);

    Connection pool2conn1 = connectionPool2.borrowConnection();
    assertThrows(IllegalArgumentException.class, () -> connectionPool1.returnConnection(pool2conn1));
    assertThrows(IllegalArgumentException.class, () -> connectionPool2.returnConnection(poolconn1));
    assertEquals(connectionPool1.getCurrentFreePoolSize(), 5-1);
    assertEquals(connectionPool2.getCurrentFreePoolSize(), 5-1);
  }

  @Test
  void testMulipleDBMultipleConnectionPools() {
    ConnectionFactory mySQLConnectionFactory = new MySQLConnectionFactory();
    ConnectionFactory postgreSQLConnectionFactory = new PostgreSQLConnectionFactory();
    ConnectionPool mysqlConnectionPool = new ConnectionPool(3, mySQLConnectionFactory, url1);
    ConnectionPool postgreConnectionPool = new ConnectionPool(5, postgreSQLConnectionFactory, url2);
    assertNotEquals(mysqlConnectionPool, postgreConnectionPool);

    Connection mysqlConn = mysqlConnectionPool.borrowConnection();
    Connection poolconn2 = mysqlConnectionPool.borrowConnection();
    assertEquals(mysqlConnectionPool.getCurrentFreePoolSize(), 3-2);

    mysqlConnectionPool.returnConnection(poolconn2);
    assertEquals(mysqlConnectionPool.getCurrentFreePoolSize(), 3-1);

    Connection postgreConn = postgreConnectionPool.borrowConnection();
    assertThrows(IllegalArgumentException.class, () -> mysqlConnectionPool.returnConnection(postgreConn));
    assertThrows(IllegalArgumentException.class, () -> postgreConnectionPool.returnConnection(mysqlConn));
    assertEquals(mysqlConnectionPool.getCurrentFreePoolSize(), 3-1);
    assertEquals(postgreConnectionPool.getCurrentFreePoolSize(), 5-1);
  }

  @Test
  void testShutDownConnectionPool() throws InterruptedException {
    ConnectionFactory mySQLConnectionFactory = new MySQLConnectionFactory();
    ConnectionPool mysqlConnectionPool = new ConnectionPool(3, mySQLConnectionFactory, url1);

    Connection mysqlConn = mysqlConnectionPool.borrowConnection();
    assertEquals(mysqlConn.read(), mySQLData);

    mysqlConnectionPool.shutdown();
    assertNull(mysqlConnectionPool.borrowConnection());
    assertEquals(mysqlConn.read(), mySQLData);

    mysqlConn = null; // This causes the real connection to be GCed
    Runtime.getRuntime().gc(); // Invoke GC. This should cause to print 'Closing connection'
  }
}