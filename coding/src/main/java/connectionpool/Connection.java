package connectionpool;

public interface Connection {
  String read();
  void close(); // closes the connection
}