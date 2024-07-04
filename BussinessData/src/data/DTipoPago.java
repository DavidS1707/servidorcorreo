/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import postgresqlconnection.SqlConnection;

/**
 *
 * @author suarez
 */
public class DTipoPago {

    private SqlConnection connection;

    public DTipoPago() {
        connection = new SqlConnection("grupo23sa", "grup023grup023", "mail.tecnoweb.org.bo",
                "5432", "db_grupo23sa");
    }

    // id, name, description
    public void create(String name, String description) throws SQLException {
        String query = "INSERT INTO TypePayments(name, description) values(?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, description);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DTypePayments.java: Error al intentar crear un tipo de pago. create(). ");
            throw new SQLException();
        }
    }

    public void edit(int id, String name, String description) throws SQLException {
        String query = "UPDATE TypePayments SET name=?, description=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, description);
        ps.setInt(3, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DTypePayments.java: Error al intentar actualizar el tipo de pago. edit(). ");
            throw new SQLException();
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM TypePayments WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DTypePayments.java: Error al intentar eliminar el tipo de pago. delete(). ");
            throw new SQLException();
        }
    }

    public List<String[]> show() throws SQLException {
        String query = "SELECT * FROM TypePayments";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<String[]> typePayments = new ArrayList<>();
        while (rs.next()) {
            String[] typePayment = new String[3];
            typePayment[0] = String.valueOf(rs.getInt("id"));
            typePayment[1] = rs.getString("name");
            typePayment[2] = rs.getString("description");
            typePayments.add(typePayment);
        }

        return typePayments;
    }

    public void disconnect() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
