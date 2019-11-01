package connectionpool.postgresql;

import connectionpool.connection.ConnectionFactory;

public class PostgreSQLConnectionFactory implements ConnectionFactory {

  public PostgreSQLConnectionImpl createConnection(String url) {
    System.out.println("Creating PostgreSQL Connection");
    return new PostgreSQLConnectionImpl(url);
  }
}
