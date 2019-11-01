package connectionpool.mysql;

import connectionpool.connection.ConnectionFactory;

public class MySQLConnectionFactory implements ConnectionFactory {

  /* Creates a MYSQL connection */
  public MySQLConnectionImpl createConnection(String url) {
    System.out.println("Creating MYSQL Connection");
    return new MySQLConnectionImpl(url);
  }
}
