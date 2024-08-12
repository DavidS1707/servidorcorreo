/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author suarez
 */
import postgresqlconnection.SqlConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DComando {

    public static final String[] HEADERS
            = {"ID", "NRO", "CASO DE USO", "ACCION", "PARAMETRO", "EJEMPLO"};

    private final SqlConnection connection;

    public DComando() {
        connection = new SqlConnection("grupo23sa", "grup023grup023*", "mail.tecnoweb.org.bo", "5432", "db_grupo23sa");
//        connection = new DBConeccion("postgres", "1234", "127.0.0.1", "5432", "db_tecno");    
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> notas = new ArrayList<>();
        String query = "SELECT * FROM comando";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            notas.add(new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("nro"),
                set.getString("caso_uso"),
                set.getString("accion"),
                set.getString("parametros"),
                set.getString("ejemplo"),});
        }
        return notas;
//        List<String[]> notas = new ArrayList<>();
//
//        notas.add(new String[]{"0", "help", "guia", "muestra los comandos disponibles", "help"});
//
//        // Comandos para los diferentes casos de uso
//        notas.add(new String[]{"1", "Gestion de usuarios", "agregar", "nombre, correo, contraseña, puntos", "agregar usuario [Pedro; pedrito@gmail.com; 123456; 30]"});
//        notas.add(new String[]{"1", "Gestion de usuarios", "modificar", "id, nombre, correo, contraseña, puntos", "modificar usuario [1; Pedro; pedrito@gmail.com; 123456; 30]"});
//        notas.add(new String[]{"1", "Gestion de usuarios", "listar", "__", "listar usuario"});
//        notas.add(new String[]{"1", "Gestion de usuarios", "eliminar", "id", "eliminar usuario [1]"});
//
//        notas.add(new String[]{"2", "Gestion de proyectos", "agregar", "nombre, descripcion, cover_url, video_url, status", "agregar proyecto [Calle 7; programa de competencia; url de la portada; url del video; edition]"});
//        notas.add(new String[]{"2", "Gestion de proyectos", "modificar", "id, nombre, descripcion, cover_url, video_url, status", "modificar proyecto [1; Calle 7; programa de competencia; url de la portada; url del video; edition]"});
//        notas.add(new String[]{"2", "Gestion de proyectos", "listar", "__", "listar proyecto"});
//        notas.add(new String[]{"2", "Gestion de proyectos", "eliminar", "id", "eliminar proyecto [1]"});
//
//        notas.add(new String[]{"3", "Gestion de presentadores", "agregar", "nombre, photo_url, sexo", "agregar presentador [Antonio Perez; url de la foto; hombre]"});
//        notas.add(new String[]{"3", "Gestion de presentadores", "modificar", "id, nombre, photo_url, sexo", "modificar presentador [1; Antonio Perez; url de la foto; hombre]"});
//        notas.add(new String[]{"3", "Gestion de presentadores", "listar", "__", "listar presentador"});
//        notas.add(new String[]{"3", "Gestion de presentadores", "eliminar", "id", "eliminar presentador [1]"});
//
//        notas.add(new String[]{"4", "Gestion de noticias", "agregar", "titulo, contenido, fecha de publicacion", "agregar noticia [noticia del dia; contenido de la noticia; aaaa-mm-dd]"});
//        notas.add(new String[]{"4", "Gestion de noticias", "modificar", "id, titulo, contenido, fecha de publicacion", "modificar noticia [1; noticia del dia; contenido de la noticia; aaaa-mm-dd]"});
//        notas.add(new String[]{"4", "Gestion de noticias", "listar", "__", "listar noticia"});
//        notas.add(new String[]{"4", "Gestion de noticias", "eliminar", "id", "eliminar noticia [1]"});
//
//        notas.add(new String[]{"5", "Gestion de contenido", "agregar", "titulo, url del video, tipo", "agregar contenido [presentacion; video url; presenter]"});
//        notas.add(new String[]{"5", "Gestion de contenido", "modificar", "id, titulo, url del video, tipo", "modificar contenido [1; presentacion; video url; presenter]"});
//        notas.add(new String[]{"5", "Gestion de contenido", "listar", "__", "listar contenido"});
//        notas.add(new String[]{"5", "Gestion de contenido", "eliminar", "id", "eliminar contenido [1]"});
//
//        notas.add(new String[]{"6", "Gestion de suscripciones", "agregar", "nombre, precio, duracion, descripcion", "agregar suscripcion [mensual; 10; 30; plan mensual]"});
//        notas.add(new String[]{"6", "Gestion de suscripciones", "modificar", "id, nombre, precio, duracion, descripcion", "modificar suscripcion [1; mensual; 10; 30; plan mensual]"});
//        notas.add(new String[]{"6", "Gestion de suscripciones", "listar", "__", "listar suscripcion"});
//        notas.add(new String[]{"6", "Gestion de suscripciones", "eliminar", "id", "eliminar suscripcion [1]"});
//
//        notas.add(new String[]{"7", "Gestion de pagos", "agregar", "fecha, tipo de pago(1=QR, 2=TigoMoney), nro de telefono, nombre del cliente, carnet de identidad", "agregar pago [indicar la fecha aaaa-mm-dd; 1; 73397228; Pedrito; 24837749]"});
//        notas.add(new String[]{"7", "Gestion de pagos", "modificar", "id, fecha, tipo de pago(1=QR, 2=TigoMoney), nro de telefono, nombre del cliente, carnet de identidad", "modificar pago [1; indicar la fecha aaaa-mm-dd; 1; 73397228; Pedrito; 24837749]"});
//        notas.add(new String[]{"7", "Gestion de pagos", "listar", "__", "listar pago"});
//        notas.add(new String[]{"7", "Gestion de pagos", "eliminar", "id", "eliminar pago [1]"});
//
//        notas.add(new String[]{"8", "Reportes y estadisticas", "listar", "__", "listar estadistica"});
//
//        return notas;
    }

    public void desconectar() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
