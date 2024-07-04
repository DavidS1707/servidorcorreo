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
public class DProductor {

    private SqlConnection connection;

    public DProductor() {
        connection = new SqlConnection("grupo23sa", "grup023grup023", "mail.tecnoweb.org.bo",
                "5432", "db_grupo23sa");
    }

    // id
    public void create(int userId) throws SQLException {
        String query = "INSERT INTO Productor(id) values(?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, userId);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DProductor.java: Error al intentar crear un productor. create(). ");
            throw new SQLException();
        }
    }

    public void delete(int userId) throws SQLException {
        String query = "DELETE FROM Productor WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, userId);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DProductor.java: Error al intentar eliminar el productor. delete(). ");
            throw new SQLException();
        }
    }

    public void disconnect() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
