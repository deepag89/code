package connectionpool.connection;

/** Interface to connection creation factory
* The factory subclasses can define the type of connections they want to create
 */
public interface ConnectionFactory {
  Connection createConnection(String url);
}
