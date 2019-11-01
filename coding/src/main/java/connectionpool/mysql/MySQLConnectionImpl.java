package connectionpool.mysql;

import connectionpool.connection.Connection;

public class MySQLConnectionImpl implements Connection {
  /* when closed, it's an error to read data from connection */
  boolean isOpen;

  /* DB Url */
  String url;

  MySQLConnectionImpl(String url) {
    this.url = url;
    isOpen = true;
  }

  @Override public String read() {
    if (!isOpen) {
      throw new IllegalStateException("Connection is closed");
    }
    System.out.println("Reading data");
    return "MySQL data";
  }

  @Override public void close() {
    if(isOpen) {
      System.out.println("Closing connection");
      isOpen = false;
    }
  }

  @Override protected void finalize() throws Throwable {
    close();
    super.finalize();
  }
}
