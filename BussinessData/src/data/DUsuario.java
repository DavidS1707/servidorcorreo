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
public class DUsuario {

    private SqlConnection connection;

    public DUsuario() {
        connection = new SqlConnection("grupo23sa", "grup023grup023", "mail.tecnoweb.org.bo",
                "5432", "db_grupo23sa");
    }

    //id, name, last_name, email, password, point
    public void create(String name, String last_name, String email, String password, int point, int rol_id) throws SQLException {
        String query = "INSERT INTO \"User\"(name, last_name, email, password, point, rol_id)" + "values(?,?,?,?,?, ?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, last_name);
        ps.setString(3, email);
        ps.setString(4, password);
        ps.setInt(5, point);
        ps.setInt(6, rol_id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DUsuario.java: Error al intentar crear un usuario. create(). ");
            throw new SQLException();
        }
    }

    //metodo para editar un usuario
    public void edit(int id, String name, String last_name, String email, String password, int point, int rol_id) throws SQLException {
        String query = "UPDATE \"User\" SET name=?, SET last_name=?, SET email=?, SET password=?, SET point=?, SET rol_id=?" + "WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, last_name);
        ps.setString(3, email);
        ps.setString(4, password);
        ps.setInt(5, point);
        ps.setInt(6, rol_id);
        ps.setInt(7, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DUsuario.java: Error al intentar actualizar el usuarion. update(). ");
            throw new SQLException();
        }
    }

    //metodo para eliminar un usuario por su id
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM \"User\" WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DUsuario.java: Error al intentar eliminar el usuario. delete(). ");
            throw new SQLException();
        }
    }

    //metodo para ver todos los usuarios
    public List<String[]> show() throws SQLException {
        String query = "SELECT * FROM \"User\"";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<String[]> users = new ArrayList<>();
        while (rs.next()) {
            String[] user = new String[7];
            user[0] = String.valueOf(rs.getInt("id"));
            user[1] = rs.getString("name");
            user[2] = rs.getString("last_name");
            user[3] = rs.getString("email");
            user[4] = rs.getString("password");
            user[5] = rs.getString("point");
            user[6] = rs.getString("rol_id");
            users.add(user);
        }

        return users;
    }

    //metodo para ver un usuario por su id
    public String[] verUsuario(int id) throws SQLException {
        String[] user = null;
        String query = "SELECT * FROM \"User\" WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            user = new String[6];
            user[0] = String.valueOf(rs.getInt("id"));
            user[1] = rs.getString("name");
            user[2] = rs.getString("last_name");
            user[3] = rs.getString("email");
            user[4] = rs.getString("password");
            user[5] = rs.getString("point");
            user[6] = rs.getString("rol_id");
        }
        return user;
    }

    //metodo para devolver el id de un usuario por su correo
    public int getIdByEmail(String email) throws SQLException {
        int id = -1;
        String query = "SELECT * FROM \"User\" WHERE email=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public void disconnect() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
