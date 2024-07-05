package postgresqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author suarez
 */
public class SqlConnection {

    private static final String DRIVER = "jdbc:postgresql://";
    private Connection connection;
    private String user;
    private String password;
    private String host;
    private String port;
    private String name;
    private String url;

    public SqlConnection(String user, String password, String host, String port, String name) {
        this.user = user; //user: postgres
        this.password = password; //password: 123456
        this.host = host; //127.0.0.1
        this.port = port; //5432
        this.name = name; //database: tecno_db
        this.url = DRIVER + host + ":" + port + "/" + name; //127.0.0.1:8000/tecno_db
    }

    public Connection connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.err.print("Class SQLConnection.java: Error al intentar establecer una conexion. " + ex);
        }
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.print("Class SQLConnection.java: Error al cerrar la conexion. ");
        }
    }
}
