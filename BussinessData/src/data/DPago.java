/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.text.ParseException;
import postgresqlconnection.SqlConnection;
import utils.DateString;

/**
 *
 * @author suarez
 */
public class DPago {

    private final SqlConnection connection;
    public static final String[] HEADERS = {"id", "date", "type_payment"};

    public DPago() {
        connection = new SqlConnection("grupo23sa", "grup023grup023*", "mail.tecnoweb.org.bo",
                "5432", "db_grupo23sa");
    }

    // id, date, type_payment, phone, customer_name, customer_ci
    public void create(String date, int type_payment, String phone, String customer_name, String customer_ci) throws SQLException, ParseException {
        String query = "INSERT INTO payments(date, type_payment) values(?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setDate(1, (Date) DateString.StringToDateSQL(date));
        ps.setInt(2, type_payment);
        ps.setString(3, phone);
        ps.setString(4, customer_name);
        ps.setString(5, customer_ci);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DPago.java: Error al intentar crear un tipo de pago. create(). ");
            throw new SQLException();
        }
    }

    public void edit(int id, String date, int type_payment, String phone, String customer_name, String customer_ci) throws SQLException, ParseException {
        String query = "UPDATE payments SET date=?, type_payment=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setDate(1, (Date) DateString.StringToDateSQL(date));
        ps.setInt(2, type_payment);
        ps.setString(3, phone);
        ps.setString(4, customer_name);
        ps.setString(5, customer_ci);
        ps.setInt(6, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DPago.java: Error al intentar actualizar el tipo de pago. edit(). ");
            throw new SQLException();
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM payments WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DPago.java: Error al intentar eliminar el tipo de pago. delete(). ");
            throw new SQLException();
        }
    }

    public List<String[]> show() throws SQLException {
        String query = "SELECT * FROM payments";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<String[]> Payments = new ArrayList<>();
        while (rs.next()) {
            String[] payments = new String[3];
            payments[0] = String.valueOf(rs.getInt("id"));
            payments[1] = rs.getString("date");
            payments[2] = String.valueOf(rs.getInt("type_payment"));
            payments[3] = rs.getString("phone");
            payments[4] = rs.getString("customer_name");
            payments[5] = rs.getString("customer_ci");
            Payments.add(payments);
        }

        return Payments;
    }

    public void disconnect() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
