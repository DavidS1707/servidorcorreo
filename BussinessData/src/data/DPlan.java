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
public class DPlan {

    private SqlConnection connection;

    public DPlan() {
        connection = new SqlConnection("grupo23sa", "grup023grup023", "mail.tecnoweb.org.bo",
                "5432", "db_grupo23sa");
    }

    // id, name, amount, description
    public void create(String name, double amount, String description) throws SQLException {
        String query = "INSERT INTO Plan(name, amount, description) values(?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, name);
        ps.setDouble(2, amount);
        ps.setString(3, description);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DPlan.java: Error al intentar crear un plan. create(). ");
            throw new SQLException();
        }
    }

    public void edit(int id, String name, double amount, String description) throws SQLException {
        String query = "UPDATE Plan SET name=?, amount=?, description=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, name);
        ps.setDouble(2, amount);
        ps.setString(3, description);
        ps.setInt(4, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DPlan.java: Error al intentar actualizar el plan. edit(). ");
            throw new SQLException();
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM Plan WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DPlan.java: Error al intentar eliminar el plan. delete(). ");
            throw new SQLException();
        }
    }

    public List<String[]> show() throws SQLException {
        String query = "SELECT * FROM Plan";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<String[]> plans = new ArrayList<>();
        while (rs.next()) {
            String[] plan = new String[4];
            plan[0] = String.valueOf(rs.getInt("id"));
            plan[1] = rs.getString("name");
            plan[2] = String.valueOf(rs.getDouble("amount"));
            plan[3] = rs.getString("description");
            plans.add(plan);
        }

        return plans;
    }

    public void disconnect() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
