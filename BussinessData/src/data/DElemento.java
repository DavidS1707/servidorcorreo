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
public class DElemento {

    private SqlConnection connection;

    public DElemento() {
        connection = new SqlConnection("grupo23sa", "grup023grup023*", "mail.tecnoweb.org.bo",
                "5432", "db_grupo23sa");
    }

    // id, title, video_url, type
    public void create(String title, String video_url, String type) throws SQLException {
        String query = "INSERT INTO elements(title, video_url, type) values(?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, title);
        ps.setString(2, video_url);
        ps.setString(3, type);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DElement.java: Error al intentar crear un elemento. create(). ");
            throw new SQLException();
        }
    }

    // metodo para editar un elemento
    public void edit(int id, String title, String video_url, String type) throws SQLException {
        String query = "UPDATE elements SET title=?, video_url=?, type=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, title);
        ps.setString(2, video_url);
        ps.setString(3, type);
        ps.setInt(4, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DElement.java: Error al intentar actualizar el elemento. edit(). ");
            throw new SQLException();
        }
    }

    // metodo para eliminar un elemento por su id
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM elements WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DElement.java: Error al intentar eliminar el elemento. delete(). ");
            throw new SQLException();
        }
    }

    // metodo para ver todos los elementos
    public List<String[]> show() throws SQLException {
        String query = "SELECT * FROM elements";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<String[]> elements = new ArrayList<>();
        while (rs.next()) {
            String[] element = new String[4];
            element[0] = String.valueOf(rs.getInt("id"));
            element[1] = rs.getString("title");
            element[2] = rs.getString("video_url");
            element[3] = rs.getString("type");
            elements.add(element);
        }

        return elements;
    }

    // metodo para ver un elemento por su id
    public String[] verElement(int id) throws SQLException {
        String[] element = null;
        String query = "SELECT * FROM elements WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            element = new String[4];
            element[0] = String.valueOf(rs.getInt("id"));
            element[1] = rs.getString("title");
            element[2] = rs.getString("video_url");
            element[3] = rs.getString("type");
        }
        return element;
    }

    public void disconnect() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
