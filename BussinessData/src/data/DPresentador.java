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
public class DPresentador {

    private SqlConnection connection;

    public DPresentador() {
        connection = new SqlConnection("grupo23sa", "grup023grup023", "mail.tecnoweb.org.bo",
                "5432", "db_grupo23sa");
    }

    // full_name, photo_url, sex
    public void create(String full_name, String photo_url, String sex) throws SQLException {
        String query = "INSERT INTO Presenter(full_name, photo_url, sex) values(?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, full_name);
        ps.setString(2, photo_url);
        ps.setString(3, sex);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DPresenter.java: Error al intentar crear un presentador. create(). ");
            throw new SQLException();
        }
    }

    // metodo para editar un presentador
    public void edit(int id, String full_name, String photo_url, String sex) throws SQLException {
        String query = "UPDATE Presenter SET full_name=?, photo_url=?, sex=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, full_name);
        ps.setString(2, photo_url);
        ps.setString(3, sex);
        ps.setInt(4, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DPresenter.java: Error al intentar actualizar el presentador. edit(). ");
            throw new SQLException();
        }
    }

    // metodo para eliminar un presentador por su id
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM Presenter WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DPresenter.java: Error al intentar eliminar el presentador. delete(). ");
            throw new SQLException();
        }
    }

    // metodo para ver todos los presentadores
    public List<String[]> show() throws SQLException {
        String query = "SELECT * FROM Presenter";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<String[]> presenters = new ArrayList<>();
        while (rs.next()) {
            String[] presenter = new String[4];
            presenter[0] = String.valueOf(rs.getInt("id"));
            presenter[1] = rs.getString("full_name");
            presenter[2] = rs.getString("photo_url");
            presenter[3] = rs.getString("sex");
            presenters.add(presenter);
        }

        return presenters;
    }

    // metodo para ver un presentador por su id
    public String[] verPresenter(int id) throws SQLException {
        String[] presenter = null;
        String query = "SELECT * FROM Presenter WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            presenter = new String[4];
            presenter[0] = String.valueOf(rs.getInt("id"));
            presenter[1] = rs.getString("full_name");
            presenter[2] = rs.getString("photo_url");
            presenter[3] = rs.getString("sex");
        }
        return presenter;
    }

    public void disconnect() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
