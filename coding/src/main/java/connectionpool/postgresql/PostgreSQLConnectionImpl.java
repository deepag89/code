package connectionpool.postgresql;

import connectionpool.connection.Connection;

public class PostgreSQLConnectionImpl implements Connection {
  boolean isOpen;
  String url;

  PostgreSQLConnectionImpl(String url) {
    this.url = url;
    isOpen = true;
  }

  @Override public String read() {
    if (!isOpen) {
      throw new IllegalStateException("Connection is closed");
    }
    System.out.println("Reading data");
    return "PostgreSQL data";
  }

  @Override public void close() {
    isOpen = false;
  }

  @Override protected void finalize() throws Throwable {
    close();
    super.finalize();
  }
}
