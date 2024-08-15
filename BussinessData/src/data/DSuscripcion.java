/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.List;
import postgresqlconnection.SqlConnection;

/**
 *
 * @author suarez
 */
public class DSuscripcion {

    private final SqlConnection connection;
    public static final String[] HEADERS = {"id", "name", "price", "duration", "description"};

    public DSuscripcion() {
        connection = new SqlConnection("grupo23sa", "grup023grup023*", "mail.tecnoweb.org.bo",
                "5432", "db_grupo23sa");
    }

    // id, name, price, duration, description
    public void create(String name, double price, int duration, String description) throws SQLException, ParseException {
        String query = "INSERT INTO subscriptions(name, price, description) values(?,?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, name);
        ps.setDouble(2, price);
        ps.setInt(3, duration);
        ps.setString(4, description);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DSubscription.java: Error al intentar crear una suscripción. create(). ");
            throw new SQLException();
        }
    }

    // metodo para editar una suscripción
    public void edit(int id, String name, double price, int duration, String description) throws SQLException {
        String query = "UPDATE subscriptions SET name=?, price=?, duration=?, description=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, name);
        ps.setDouble(2, price);
        ps.setInt(3, duration);
        ps.setString(4, description);
        ps.setInt(5, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DSubscription.java: Error al intentar actualizar la suscripción. update(). ");
            throw new SQLException();
        }
    }

    // metodo para eliminar una suscripción por su id
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM subscriptions WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DSubscription.java: Error al intentar eliminar la suscripción. delete(). ");
            throw new SQLException();
        }
    }

    // metodo para ver todas las suscripciones
    public List<String[]> show() throws SQLException {
        String query = "SELECT * FROM subscriptions";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<String[]> subscriptions = new ArrayList<>();
        while (rs.next()) {
            String[] subscription = new String[5];
            subscription[0] = String.valueOf(rs.getInt("id"));
            subscription[1] = rs.getString("name");
            subscription[2] = String.valueOf(rs.getDouble("price"));
            subscription[3] = String.valueOf(rs.getInt("duration"));
            subscription[4] = rs.getString("description");
            subscriptions.add(subscription);
        }

        return subscriptions;
    }

    // metodo para ver una suscripción por su id
    public String[] verSubscription(int id) throws SQLException {
        String[] subscription = null;
        String query = "SELECT * FROM subscriptions WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            subscription = new String[5];
            subscription[0] = String.valueOf(rs.getInt("id"));
            subscription[1] = rs.getString("name");
            subscription[2] = String.valueOf(rs.getDouble("price"));
            subscription[3] = String.valueOf(rs.getInt("duration"));
            subscription[4] = rs.getString("description");
        }
        return subscription;
    }

    public void disconnect() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
