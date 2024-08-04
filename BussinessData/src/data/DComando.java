/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author suarez
 */
import postgresqlconnection.SqlConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DComando {

    public static final String[] HEADERS
            = {"ID", "CASO DE USO", "ACCION", "PARAMETRO", "EJEMPLO"};

    private final SqlConnection connection;

    public DComando() {
        connection = new SqlConnection("grupo23sa", "grup023grup023", "mail.tecnoweb.org.bo", "5432", "db_grupo23sa");
//        connection = new DBConeccion("postgres", "1234", "127.0.0.1", "5432", "db_tecno");    //Local By Julico
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> notas = new ArrayList<>();
        String query = "SELECT * FROM comando";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            notas.add(new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("caso_de_uso"),
                set.getString("accion"),
                set.getString("parametro"),
                set.getString("ejemplo"),});
        }
        return notas;
    }

    public void desconectar() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
