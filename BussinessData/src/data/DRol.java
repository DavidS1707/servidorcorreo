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
public class DRol {

    private SqlConnection connection;

    public DRol() {
        connection = new SqlConnection("grupo23sa", "grup023grup023*", "mail.tecnoweb.org.bo",
                "5432", "db_grupo23sa");
    }

    // metodo para crear un Rol
    public void create(String name, String guard_name) throws SQLException {
        String query = "INSERT INTO roles(name, guard_name) values(?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, guard_name);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DRol.java: Error al intentar crear un Rol. create(). ");
            throw new SQLException();
        }
    }

    // metodo para editar un Rol
    public void edit(int id, String name, String guard_name) throws SQLException {
        String query = "UPDATE roles SET name=?, guard_name=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, guard_name);
        ps.setInt(3, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DRol.java: Error al intentar actualizar el Rol. edit(). ");
            throw new SQLException();
        }
    }

    // metodo para eliminar un Rol por su id
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM roles WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DRol.java: Error al intentar eliminar el Rol. delete(). ");
            throw new SQLException();
        }
    }

    // metodo para ver todos los Roles
    public List<String[]> show() throws SQLException {
        String query = "SELECT * FROM roles";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<String[]> roles = new ArrayList<>();
        while (rs.next()) {
            String[] rol = new String[3];
            rol[0] = String.valueOf(rs.getInt("id"));
            rol[1] = rs.getString("name");
            rol[2] = rs.getString("guard_name");
            roles.add(rol);
        }

        return roles;
    }

    // metodo para ver un Rol por su id
    public String[] verRol(int id) throws SQLException {
        String[] rol = null;
        String query = "SELECT * FROM roles WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            rol = new String[3];
            rol[0] = String.valueOf(rs.getInt("id"));
            rol[1] = rs.getString("name");
            rol[2] = rs.getString("guard_name");
        }
        return rol;
    }

    public void disconnect() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
