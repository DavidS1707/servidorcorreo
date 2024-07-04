/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import postgresqlconnection.SqlConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author suarez
 */
public class DEditor {

    private SqlConnection connection;

    public DEditor() {
        connection = new SqlConnection("grupo23sa", "grup023grup023", "mail.tecnoweb.org.bo",
                "5432", "db_grupo23sa");
    }

    // id
    public void create(int userId) throws SQLException {
        String query = "INSERT INTO Editor(id) values(?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, userId);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DEditor.java: Error al intentar crear un editor. create(). ");
            throw new SQLException();
        }
    }

    public void delete(int userId) throws SQLException {
        String query = "DELETE FROM Editor WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, userId);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DEditor.java: Error al intentar eliminar el editor. delete(). ");
            throw new SQLException();
        }
    }

    public void disconnect() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
