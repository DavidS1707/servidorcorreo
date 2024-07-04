/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.List;
import postgresqlconnection.SqlConnection;
import utils.DateString;

/**
 *
 * @author suarez
 */
public class DSuscripcion {

    private SqlConnection connection;

    public DSuscripcion() {
        connection = new SqlConnection("grupo23sa", "grup023grup023", "mail.tecnoweb.org.bo",
                "5432", "db_grupo23sa");
    }

    // id, user_id, plan_id, type_payment_id, date
    public void create(int user_id, int plan_id, int type_payment_id, String date) throws SQLException, ParseException {
        String query = "INSERT INTO Subscription(user_id, plan_id, type_payment_id, date) values(?,?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, user_id);
        ps.setInt(2, plan_id);
        ps.setInt(3, type_payment_id);
        ps.setDate(4, (Date) DateString.StringToDateSQL(date));

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DSubscription.java: Error al intentar crear una suscripción. create(). ");
            throw new SQLException();
        }
    }

    // metodo para editar una suscripción
    public void edit(int id, int user_id, int plan_id, int type_payment_id, String date) throws SQLException {
        String query = "UPDATE Subscription SET user_id=?, plan_id=?, type_payment_id=?, date=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, user_id);
        ps.setInt(2, plan_id);
        ps.setInt(3, type_payment_id);
        ps.setString(4, date);
        ps.setInt(5, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DSubscription.java: Error al intentar actualizar la suscripción. update(). ");
            throw new SQLException();
        }
    }

    // metodo para eliminar una suscripción por su id
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM Subscription WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DSubscription.java: Error al intentar eliminar la suscripción. delete(). ");
            throw new SQLException();
        }
    }

    // metodo para ver todas las suscripciones
    public List<String[]> show() throws SQLException {
        String query = "SELECT * FROM Subscription";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<String[]> subscriptions = new ArrayList<>();
        while (rs.next()) {
            String[] subscription = new String[5];
            subscription[0] = String.valueOf(rs.getInt("id"));
            subscription[1] = String.valueOf(rs.getInt("user_id"));
            subscription[2] = String.valueOf(rs.getInt("plan_id"));
            subscription[3] = String.valueOf(rs.getInt("type_payment_id"));
            subscription[4] = rs.getString("date");
            subscriptions.add(subscription);
        }

        return subscriptions;
    }

    // metodo para ver una suscripción por su id
    public String[] verSubscription(int id) throws SQLException {
        String[] subscription = null;
        String query = "SELECT * FROM Subscription WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            subscription = new String[5];
            subscription[0] = String.valueOf(rs.getInt("id"));
            subscription[1] = String.valueOf(rs.getInt("user_id"));
            subscription[2] = String.valueOf(rs.getInt("plan_id"));
            subscription[3] = String.valueOf(rs.getInt("type_payment_id"));
            subscription[4] = rs.getString("date");
        }
        return subscription;
    }

    public void disconnect() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
