/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;
import postgresqlconnection.SqlConnection;

/**
 *
 * @author suarez
 */
public class DProyecto {

    private final SqlConnection connection;
    public static final String[] HEADERS = {"id", "name", "description", "cover_url", "video_url"};

    public DProyecto() {
        connection = new SqlConnection("grupo23sa", "grup023grup023*", "mail.tecnoweb.org.bo",
                "5432", "db_grupo23sa");
    }

    // id, name, description, cover_url, video_url
    public void create(String name, String description, String cover_url, String video_url) throws SQLException {
        String query = "INSERT INTO projects(name, description, cover_url, video_url) values(?,?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, description);
        ps.setString(3, cover_url);
        ps.setString(4, video_url);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DProject.java: Error al intentar crear un proyecto. create(). ");
            throw new SQLException();
        }
    }

    // metodo para editar un proyecto
    public void edit(int id, String name, String description, String cover_url, String video_url, String status) throws SQLException {
        String query = "UPDATE projects SET name=?, description=?, cover_url=?, video_url=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, description);
        ps.setString(3, cover_url);
        ps.setString(4, video_url);
        ps.setInt(5, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DProject.java: Error al intentar actualizar el proyecto. update(). ");
            throw new SQLException();
        }
    }

    // metodo para eliminar un proyecto por su id
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM projects WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DProject.java: Error al intentar eliminar el proyecto. delete(). ");
            throw new SQLException();
        }
    }

    // metodo para ver todos los proyectos
    public List<String[]> show() throws SQLException {
        String query = "SELECT * FROM projects";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<String[]> projects = new ArrayList<>();
        while (rs.next()) {
            String[] project = new String[6];
            project[0] = String.valueOf(rs.getInt("id"));
            project[1] = rs.getString("name");
            project[2] = rs.getString("description");
            project[3] = rs.getString("cover_url");
            project[4] = rs.getString("video_url");
            projects.add(project);
        }

        return projects;
    }

    // metodo para ver un proyecto por su id
    public String[] verProject(int id) throws SQLException {
        String[] project = null;
        String query = "SELECT * FROM projects WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            project = new String[6];
            project[0] = String.valueOf(rs.getInt("id"));
            project[1] = rs.getString("name");
            project[2] = rs.getString("description");
            project[3] = rs.getString("cover_url");
            project[4] = rs.getString("video_url");
        }
        return project;
    }

    public void disconnect() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
