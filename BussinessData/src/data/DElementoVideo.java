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
public class DElementoVideo {

    private final SqlConnection connection;

    public DElementoVideo() {
        connection = new SqlConnection("grupo23sa", "grup023grup023*", "mail.tecnoweb.org.bo",
                "5432", "db_grupo23sa");
    }

    // metodo para crear un ElementVideo
    public void create(String title, String video_url) throws SQLException {
        String query = "INSERT INTO ElementVideo(title, video_url) values(?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, title);
        ps.setString(2, video_url);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DElementVideo.java: Error al intentar crear un ElementVideo. create(). ");
            throw new SQLException();
        }
    }

    // metodo para editar un ElementVideo
    public void edit(int id, String title, String video_url) throws SQLException {
        String query = "UPDATE ElementVideo SET title=?, video_url=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, title);
        ps.setString(2, video_url);
        ps.setInt(3, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DElementVideo.java: Error al intentar actualizar el ElementVideo. edit(). ");
            throw new SQLException();
        }
    }

    // metodo para eliminar un ElementVideo por su id
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM ElementVideo WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DElementVideo.java: Error al intentar eliminar el ElementVideo. delete(). ");
            throw new SQLException();
        }
    }

    // metodo para ver todos los ElementVideo
    public List<String[]> show() throws SQLException {
        String query = "SELECT * FROM ElementVideo";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<String[]> elementVideos = new ArrayList<>();
        while (rs.next()) {
            String[] elementVideo = new String[3];
            elementVideo[0] = String.valueOf(rs.getInt("id"));
            elementVideo[1] = rs.getString("title");
            elementVideo[2] = rs.getString("video_url");
            elementVideos.add(elementVideo);
        }

        return elementVideos;
    }

    // metodo para ver un ElementVideo por su id
    public String[] verElementVideo(int id) throws SQLException {
        String[] elementVideo = null;
        String query = "SELECT * FROM ElementVideo WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            elementVideo = new String[3];
            elementVideo[0] = String.valueOf(rs.getInt("id"));
            elementVideo[1] = rs.getString("title");
            elementVideo[2] = rs.getString("video_url");
        }
        return elementVideo;
    }

    public void disconnect() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
