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
public class DElementoPresentador {

    private SqlConnection connection;

    public DElementoPresentador() {
        connection = new SqlConnection("grupo23sa", "grup023grup023", "mail.tecnoweb.org.bo",
                "5432", "db_grupo23sa");
    }

    // content, expression, video_id
    public void create(String content, String expression, int video_id) throws SQLException {
        String query = "INSERT INTO ElementPresenter(content, expression, video_id) values(?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, content);
        ps.setString(2, expression);
        ps.setInt(3, video_id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DElementPresenter.java: Error al intentar crear un ElementPresenter. create(). ");
            throw new SQLException();
        }
    }

    // metodo para editar un ElementPresenter
    public void edit(int id, String content, String expression, int video_id) throws SQLException {
        String query = "UPDATE ElementPresenter SET content=?, expression=?, video_id=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, content);
        ps.setString(2, expression);
        ps.setInt(3, video_id);
        ps.setInt(4, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DElementPresenter.java: Error al intentar actualizar el ElementPresenter. edit(). ");
            throw new SQLException();
        }
    }

    // metodo para eliminar un ElementPresenter por su id
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM ElementPresenter WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DElementPresenter.java: Error al intentar eliminar el ElementPresenter. delete(). ");
            throw new SQLException();
        }
    }

    // metodo para ver todos los ElementPresenter
    public List<String[]> show() throws SQLException {
        String query = "SELECT * FROM ElementPresenter";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<String[]> elementPresenters = new ArrayList<>();
        while (rs.next()) {
            String[] elementPresenter = new String[4];
            elementPresenter[0] = String.valueOf(rs.getInt("id"));
            elementPresenter[1] = rs.getString("content");
            elementPresenter[2] = rs.getString("expression");
            elementPresenter[3] = String.valueOf(rs.getInt("video_id"));
            elementPresenters.add(elementPresenter);
        }

        return elementPresenters;
    }

    // metodo para ver un ElementPresenter por su id
    public String[] verElementPresenter(int id) throws SQLException {
        String[] elementPresenter = null;
        String query = "SELECT * FROM ElementPresenter WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            elementPresenter = new String[4];
            elementPresenter[0] = String.valueOf(rs.getInt("id"));
            elementPresenter[1] = rs.getString("content");
            elementPresenter[2] = rs.getString("expression");
            elementPresenter[3] = String.valueOf(rs.getInt("video_id"));
        }
        return elementPresenter;
    }

    public void disconnect() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
