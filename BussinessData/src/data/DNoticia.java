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
public class DNoticia {

    private final SqlConnection connection;
    public static final String[] HEADERS = {"id", "title", "content", "publication_date"};

    public DNoticia() {
        connection = new SqlConnection("grupo23sa", "grup023grup023*", "mail.tecnoweb.org.bo",
                "5432", "db_grupo23sa");
    }

    // id, title, content, publication_date
    public void create(String title, String content, String publication_date) throws SQLException, ParseException {
        String query = "INSERT INTO news(title, content, publication_date) values(?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, title);
        ps.setString(2, content);
        ps.setDate(3, (Date) DateString.StringToDateSQL(publication_date));

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DNews.java: Error al intentar crear una noticia. create(). ");
            throw new SQLException();
        }
    }

    // metodo para editar una noticia
    public void edit(int id, String title, String content, String publication_date) throws SQLException, ParseException {
        String query = "UPDATE news SET title=?, content=?, publication_date=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, title);
        ps.setString(2, content);
        ps.setDate(3, (Date) DateString.StringToDateSQL(publication_date));
        ps.setInt(4, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DNews.java: Error al intentar actualizar la noticia. edit(). ");
            throw new SQLException();
        }
    }

    // metodo para eliminar una noticia por su id
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM news WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.print("Class DNews.java: Error al intentar eliminar la noticia. delete(). ");
            throw new SQLException();
        }
    }

    // metodo para ver todas las noticias
    public List<String[]> show() throws SQLException {
        String query = "SELECT * FROM news";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        List<String[]> newsList = new ArrayList<>();
        while (rs.next()) {
            String[] news = new String[4];
            news[0] = String.valueOf(rs.getInt("id"));
            news[1] = rs.getString("title");
            news[2] = rs.getString("content");
            news[3] = rs.getString("publication_date");
            newsList.add(news);
        }

        return newsList;
    }

    // metodo para ver una noticia por su id
    public String[] verNews(int id) throws SQLException {
        String[] news = null;
        String query = "SELECT * FROM news WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            news = new String[4];
            news[0] = String.valueOf(rs.getInt("id"));
            news[1] = rs.getString("title");
            news[2] = rs.getString("content");
            news[3] = rs.getString("publication_date");
        }
        return news;
    }

    public void disconnect() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
