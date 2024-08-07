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
public class DCategoriaNoticia {

    private SqlConnection connection;

    public DCategoriaNoticia() {
        connection = new SqlConnection("grupo23sa", "grup023grup023*", "mail.tecnoweb.org.bo",
                "5432", "db_grupo23sa");
    }

    // id, name, description
    public void create(String name, String description) throws SQLException {
        String query = "INSERT INTO news_categories(name, description) values(?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, description);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DCategoryNews.java: Error al intentar crear una categoría de noticia. create(). ");
            throw new SQLException();
        }
    }

    // metodo para editar una categoría de noticia
    public void edit(int id, String name, String description) throws SQLException {
        String query = "UPDATE news_categories SET name=?, description=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, description);
        ps.setInt(3, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DCategoryNews.java: Error al intentar actualizar la categoría de noticia. edit(). ");
            throw new SQLException();
        }
    }

    // metodo para eliminar una categoría de noticia por su id
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM news_categories WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DCategoryNews.java: Error al intentar eliminar la categoría de noticia. delete(). ");
            throw new SQLException();
        }
    }

    // metodo para ver todas las categorías de noticias
    public List<String[]> show() throws SQLException {
        String query = "SELECT * FROM news_categories";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<String[]> categories = new ArrayList<>();
        while (rs.next()) {
            String[] category = new String[3];
            category[0] = String.valueOf(rs.getInt("id"));
            category[1] = rs.getString("name");
            category[2] = rs.getString("description");
            categories.add(category);
        }

        return categories;
    }

    // metodo para ver una categoría de noticia por su id
    public String[] verCategoryNews(int id) throws SQLException {
        String[] category = null;
        String query = "SELECT * FROM news_categories WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            category = new String[3];
            category[0] = String.valueOf(rs.getInt("id"));
            category[1] = rs.getString("name");
            category[2] = rs.getString("description");
        }
        return category;
    }

    public void disconnect() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
