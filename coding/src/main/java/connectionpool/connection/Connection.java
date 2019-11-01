package connectionpool.connection;

/** Generic Connection Interface. */
public interface Connection {

  /* Read data from the connection */
  String read();

  /* Close the connection */
  void close();
}