/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemacentral;

import bussiness.BUsuario;
import communication.MailVerificationThread;
import communication.SendEmailThread;
import Interfaces.IEmailEventListener;
import interpreter.analex.Interpreter;
import interpreter.analex.interfaces.ITokenEventListener;
import interpreter.analex.utils.Token;
import interpreter.events.TokenEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import utils.Email;
import Utils.HtmlBuilder;
import bussiness.BComando;
import bussiness.BContenido;
import bussiness.BEstadistica;
import bussiness.BNoticia;
import bussiness.BPago;
import bussiness.BPresentador;
import bussiness.BProyecto;
import bussiness.BSuscripcion;
import data.DComando;
import data.DElemento;
import data.DNoticia;
import data.DPago;
import data.DPresentador;
import data.DProyecto;
import data.DSuscripcion;
import data.DUsuario;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author suarez
 */
public class MailApplication implements IEmailEventListener, ITokenEventListener {

    private static final int CONSTRAINTS_ERROR = -2;
    private static final int NUMBER_FORMAT_ERROR = -3;
    private static final int INDEX_OUT_OF_BOUND_ERROR = -4;
    private static final int PARSE_ERROR = -5;
    private static final int AUTHORIZATION_ERROR = -6;

    private final MailVerificationThread mailVerificationThread;

    private final BUsuario bUsuario;
    private final BContenido bContenido;
    private final BEstadistica bEstadistica;
    private final BNoticia bNoticia;
    private final BPago bPago;
    private final BPresentador bPresentador;
    private final BComando bComando;
    private final BProyecto bProyecto;
    private final BSuscripcion bSuscripcion;

    public MailApplication() {
        mailVerificationThread = new MailVerificationThread();
        mailVerificationThread.setEmailEventListener(MailApplication.this);

        bUsuario = new BUsuario();
        bComando = new BComando();
        bContenido = new BContenido();
        bEstadistica = new BEstadistica();
        bNoticia = new BNoticia();
        bPago = new BPago();
        bPresentador = new BPresentador();
        bProyecto = new BProyecto();
        bSuscripcion = new BSuscripcion();
    }

    public void start() {
        Thread thread = new Thread(mailVerificationThread);
        thread.setName("Mail Verfication Thread");
        thread.start();
    }

    @Override
    public void onReceiveEmailEvent(List<Email> emails) {
        for (Email email : emails) {
            String subject = email.getSubject() + " ";
            Interpreter interpreter = new Interpreter(subject, email.getFrom());
            interpreter.setListener(MailApplication.this);
            Thread thread = new Thread(interpreter);
            thread.setName("Interpreter Thread");
            thread.start();
        }
    }

    @Override
    public void help(TokenEvent event) {
        System.out.println("HELP");
        try {
            System.out.println(event);
            ArrayList<String[]> lista = (ArrayList<String[]>) bComando.listar();
            String s = "";
            for (int i = 0; i < lista.size(); i++) {
                s = s + "[" + i + "] : ";
                for (String get : lista.get(i)) {
                    s = s + get + " | ";
                }
                s = s + "\n";
            }
            System.out.println(s);
            tableNotifySuccess(event.getSender(), "Lista de Comandos", DComando.HEADERS, bComando.listar());
        } catch (SQLException ex) {
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        }
    }

    @Override
    public void usuario(TokenEvent event) {
        System.out.println("CU: USUARIO");
        System.out.println(event);
        try {
            switch (event.getAction()) {
                case Token.AGREGAR -> {
                    bUsuario.create(event.getParams());
                    System.out.println("Usuario registrado correctamente!");
                    simpleNotifySuccess(event.getSender(), "Usuario registrado correctamente!");
                    break;
                }
                case Token.MODIFICAR -> {
                    bUsuario.edit(event.getParams());
                    System.out.println("Usuario modificado correctamente!");
                    simpleNotifySuccess(event.getSender(), "Usuario modificado correctamente!");
                    break;
                }
                case Token.LISTAR -> {
                    ArrayList<String[]> lista = (ArrayList<String[]>) bUsuario.show();
                    String s = "";
                    for (int i = 0; i < lista.size(); i++) {
                        s = s + "[" + i + "] : ";
                        for (String get : lista.get(i)) {
                            s = s + get + " | ";
                        }
                        s = s + "\n";
                    }
                    System.out.println(s);
                    tableNotifySuccess(event.getSender(), "Lista de Usuarios: ", DUsuario.HEADERS, (ArrayList<String[]>) bUsuario.show());
                    break;
                }
                case Token.ELIMINAR -> {
                    bUsuario.delete(event.getParams());
                    System.out.println("Usuario eliminado correctamente!");
                    simpleNotifySuccess(event.getSender(), "Usuario eliminado correctamente!");
                    break;
                }
                default ->
                    System.out.println("La accion no es valida para el caso de uso");
            }
        } catch (SQLException ex) {
            System.out.println("Mensaje: " + ex.getSQLState());
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        }
    }

    @Override
    public void suscripcion(TokenEvent event) {
        System.out.println("CU: SUSCRIPCION");
        System.out.println(event);
        try {
            switch (event.getAction()) {
                case Token.AGREGAR -> {
                    bSuscripcion.create(event.getParams());
                    System.out.println("Suscripcion registrada correctamente!");
                    simpleNotifySuccess(event.getSender(), "Suscripcion registrada correctamente!");
                    break;
                }
                case Token.MODIFICAR -> {
                    bSuscripcion.edit(event.getParams());
                    System.out.println("Suscripcion modificada correctamente!");
                    simpleNotifySuccess(event.getSender(), "Suscripcion modificada correctamente!");
                    break;
                }
                case Token.LISTAR -> {
                    ArrayList<String[]> lista = (ArrayList<String[]>) bSuscripcion.show();
                    String s = "";
                    for (int i = 0; i < lista.size(); i++) {
                        s = s + "[" + i + "] : ";
                        for (String get : lista.get(i)) {
                            s = s + get + " | ";
                        }
                        s = s + "\n";
                    }
                    System.out.println(s);
                    tableNotifySuccess(event.getSender(), "Lista de Suscripciones: ", DSuscripcion.HEADERS, (ArrayList<String[]>) bSuscripcion.show());
                    break;
                }
                case Token.ELIMINAR -> {
                    bUsuario.delete(event.getParams());
                    System.out.println("Suscripcion eliminada correctamente!");
                    simpleNotifySuccess(event.getSender(), "Suscripcion eliminada correctamente!");
                    break;
                }
                default ->
                    System.out.println("La accion no es valida para el caso de uso");
            }
        } catch (SQLException ex) {
            System.out.println("Mensaje: " + ex.getSQLState());
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        } catch (ParseException ex) {
            Logger.getLogger(MailApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contenido(TokenEvent event) {
        System.out.println("CU: CONTENIDO");
        System.out.println(event);
        try {
            switch (event.getAction()) {
                case Token.AGREGAR -> {
                    bContenido.create(event.getParams());
                    System.out.println("Contenido registrado correctamente!");
                    simpleNotifySuccess(event.getSender(), "Contenido registrado correctamente!");
                    break;
                }
                case Token.MODIFICAR -> {
                    bContenido.edit(event.getParams());
                    System.out.println("Contenido modificado correctamente!");
                    simpleNotifySuccess(event.getSender(), "Contenido modificado correctamente!");
                    break;
                }
                case Token.LISTAR -> {
                    ArrayList<String[]> lista = (ArrayList<String[]>) bContenido.show();
                    String s = "";
                    for (int i = 0; i < lista.size(); i++) {
                        s = s + "[" + i + "] : ";
                        for (String get : lista.get(i)) {
                            s = s + get + " | ";
                        }
                        s = s + "\n";
                    }
                    System.out.println(s);
                    tableNotifySuccess(event.getSender(), "Lista de Contenidos: ", DElemento.HEADERS, (ArrayList<String[]>) bContenido.show());
                    break;
                }
                case Token.ELIMINAR -> {
                    bUsuario.delete(event.getParams());
                    System.out.println("Contenido eliminado correctamente!");
                    simpleNotifySuccess(event.getSender(), "Contenido eliminado correctamente!");
                    break;
                }
                default ->
                    System.out.println("La accion no es valida para el caso de uso");
            }
        } catch (SQLException ex) {
            System.out.println("Mensaje: " + ex.getSQLState());
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        }
    }

    @Override
    public void estadistica(TokenEvent event) {
        System.out.println("CU: ESTADISTICA");
        System.out.println(event);
        if (event.getAction() == Token.LISTAR) {
            ArrayList<String[]> lista = null;
            try {
                lista = (ArrayList<String[]>) bEstadistica.showNoticias();
            } catch (SQLException ex) {
                Logger.getLogger(MailApplication.class.getName()).log(Level.SEVERE, null, ex);
            }

            String s = "";
            for (int i = 0; i < lista.size(); i++) {
                s = s + "[" + i + "] : ";
                for (String get : lista.get(i)) {
                    s = s + get + " | ";
                }
                s = s + "\n";
            }
            System.out.println(s);
            try {
                tableNotifySuccess(event.getSender(), "Reportes y Estadisticas: ", DNoticia.HEADERS, (ArrayList<String[]>) bEstadistica.showNoticias());
            } catch (SQLException ex) {
                Logger.getLogger(MailApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("La accion no es valida para el caso de uso");
            //enviar al correo una notificacion
        } //enviar notificacion de error
        //enviar notificacion de error
    }

    @Override
    public void noticia(TokenEvent event) {
        System.out.println("CU: NOTICIA");
        System.out.println(event);
        try {
            switch (event.getAction()) {
                case Token.AGREGAR -> {
                    bNoticia.create(event.getParams());
                    System.out.println("Noticia registrada correctamente!");
                    simpleNotifySuccess(event.getSender(), "Noticia registrada correctamente!");
                    break;
                }
                case Token.MODIFICAR -> {
                    bNoticia.edit(event.getParams());
                    System.out.println("Noticia modificada correctamente!");
                    simpleNotifySuccess(event.getSender(), "Noticia modificada correctamente!");
                    break;
                }
                case Token.LISTAR -> {
                    ArrayList<String[]> lista = (ArrayList<String[]>) bNoticia.show();
                    String s = "";
                    for (int i = 0; i < lista.size(); i++) {
                        s = s + "[" + i + "] : ";
                        for (String get : lista.get(i)) {
                            s = s + get + " | ";
                        }
                        s = s + "\n";
                    }
                    System.out.println(s);
                    tableNotifySuccess(event.getSender(), "Lista de Noticias: ", DNoticia.HEADERS, (ArrayList<String[]>) bNoticia.show());
                    break;
                }
                case Token.ELIMINAR -> {
                    bNoticia.delete(event.getParams());
                    System.out.println("Noticia eliminada correctamente!");
                    simpleNotifySuccess(event.getSender(), "Noticia eliminada correctamente!");
                    break;
                }
                default ->
                    System.out.println("La accion no es valida para el caso de uso");
            }
        } catch (SQLException ex) {
            System.out.println("Mensaje: " + ex.getSQLState());
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        } catch (ParseException ex) {
            Logger.getLogger(MailApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void pago(TokenEvent event) {
        System.out.println("CU: PAGO");
        System.out.println(event);
        try {
            switch (event.getAction()) {
                case Token.AGREGAR -> {
                    bPago.create(event.getParams());
                    System.out.println("Pago registrado correctamente!");
                    simpleNotifySuccess(event.getSender(), "Pago registrado correctamente!");
                    break;
                }
                case Token.MODIFICAR -> {
                    bPago.edit(event.getParams());
                    System.out.println("Pago modificado correctamente!");
                    simpleNotifySuccess(event.getSender(), "Pago modificado correctamente!");
                    break;
                }
                case Token.LISTAR -> {
                    ArrayList<String[]> lista = (ArrayList<String[]>) bPago.show();
                    String s = "";
                    for (int i = 0; i < lista.size(); i++) {
                        s = s + "[" + i + "] : ";
                        for (String get : lista.get(i)) {
                            s = s + get + " | ";
                        }
                        s = s + "\n";
                    }
                    System.out.println(s);
                    tableNotifySuccess(event.getSender(), "Lista de Pagos: ", DPago.HEADERS, (ArrayList<String[]>) bPago.show());
                    break;
                }
                case Token.ELIMINAR -> {
                    bPago.delete(event.getParams());
                    System.out.println("Pago eliminado correctamente!");
                    simpleNotifySuccess(event.getSender(), "Pago eliminado correctamente!");
                    break;
                }
                default ->
                    System.out.println("La accion no es valida para el caso de uso");
            }
        } catch (SQLException ex) {
            System.out.println("Mensaje de error en base de datos: " + ex.getSQLState());
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        } catch (ParseException ex) {
            Logger.getLogger(MailApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void presentador(TokenEvent event) {
        System.out.println("CU: PRESENTADOR");
        System.out.println(event);
        try {
            switch (event.getAction()) {
                case Token.AGREGAR -> {
                    bPresentador.create(event.getParams());
                    System.out.println("Presentador registrado correctamente!");
                    simpleNotifySuccess(event.getSender(), "Presentador registrado correctamente!");
                    break;
                }
                case Token.MODIFICAR -> {
                    bPresentador.edit(event.getParams());
                    System.out.println("Presentador modificado correctamente!");
                    simpleNotifySuccess(event.getSender(), "Presentador modificado correctamente!");
                    break;
                }
                case Token.LISTAR -> {
                    ArrayList<String[]> lista = (ArrayList<String[]>) bPresentador.show();
                    String s = "";
                    for (int i = 0; i < lista.size(); i++) {
                        s = s + "[" + i + "] : ";
                        for (String get : lista.get(i)) {
                            s = s + get + " | ";
                        }
                        s = s + "\n";
                    }
                    System.out.println(s);
                    tableNotifySuccess(event.getSender(), "Lista de Presentadores: ", DPresentador.HEADERS, (ArrayList<String[]>) bPresentador.show());
                    break;
                }
                case Token.ELIMINAR -> {
                    bPresentador.delete(event.getParams());
                    System.out.println("Presentador eliminado correctamente!");
                    simpleNotifySuccess(event.getSender(), "Presentador eliminado correctamente!");
                    break;
                }
                default ->
                    System.out.println("La accion no es valida para el caso de uso");
            }
        } catch (SQLException ex) {
            System.out.println("Mensaje: " + ex.getSQLState());
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        }
    }

    @Override
    public void proyecto(TokenEvent event) {
        System.out.println("CU: PROYECTO");
        System.out.println(event);
        try {
            switch (event.getAction()) {
                case Token.AGREGAR -> {
                    bProyecto.create(event.getParams());
                    System.out.println("Proyecto registrado correctamente!");
                    simpleNotifySuccess(event.getSender(), "Proyecto registrado correctamente!");
                    break;
                }
                case Token.MODIFICAR -> {
                    bProyecto.edit(event.getParams());
                    System.out.println("Proyecto modificado correctamente!");
                    simpleNotifySuccess(event.getSender(), "Proyecto modificado correctamente!");
                    break;
                }
                case Token.LISTAR -> {
                    ArrayList<String[]> lista = (ArrayList<String[]>) bProyecto.show();
                    String s = "";
                    for (int i = 0; i < lista.size(); i++) {
                        s = s + "[" + i + "] : ";
                        for (String get : lista.get(i)) {
                            s = s + get + " | ";
                        }
                        s = s + "\n";
                    }
                    System.out.println(s);
                    tableNotifySuccess(event.getSender(), "Lista de Proyectos: ", DProyecto.HEADERS, (ArrayList<String[]>) bProyecto.show());
                    break;
                }
                case Token.ELIMINAR -> {
                    bProyecto.delete(event.getParams());
                    System.out.println("Proyecto eliminado correctamente!");
                    simpleNotifySuccess(event.getSender(), "Proyecto eliminado correctamente!");
                    break;
                }
                default ->
                    System.out.println("La accion no es valida para el caso de uso");
            }
        } catch (SQLException ex) {
            System.out.println("Mensaje: " + ex.getSQLState());
            handleError(CONSTRAINTS_ERROR, event.getSender(), null);
        } catch (NumberFormatException ex) {
            handleError(NUMBER_FORMAT_ERROR, event.getSender(), null);
        } catch (IndexOutOfBoundsException ex) {
            handleError(INDEX_OUT_OF_BOUND_ERROR, event.getSender(), null);
        }
    }

    @Override
    public void error(TokenEvent event) {
        handleError(event.getAction(), event.getSender(), event.getParams());
    }

    private void handleError(int type, String email, List<String> args) {
        Email emailObject = null;

        switch (type) {
            case Token.ERROR_CHARACTER ->
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Caracter desconocido",
                    "No se pudo ejecutar el comando [" + args.get(0) + "] debido a: ",
                    "El caracter \"" + args.get(1) + "\" es desconocido."
                }));
            case Token.ERROR_COMMAND ->
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Comando desconocido",
                    "No se pudo ejecutar el comando [" + args.get(0) + "] debido a: ",
                    "No se reconoce la palabra \"" + args.get(1) + "\" como un comando válido"
                }));
            case CONSTRAINTS_ERROR ->
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Error al interactuar con la base de datos",
                    "Referencia a información inexistente"
                }));
            case NUMBER_FORMAT_ERROR ->
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Error en el tipo de parámetro",
                    "El tipo de uno de los parámetros es incorrecto"
                }));
            case INDEX_OUT_OF_BOUND_ERROR ->
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Cantidad de parámetros incorrecta",
                    "La cantidad de parámetros para realizar la acción es incorrecta"
                }));
            case PARSE_ERROR ->
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Error al procesar la fecha",
                    "La fecha introducida posee un formato incorrecto"
                }));
            case AUTHORIZATION_ERROR ->
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Acceso denegado",
                    "Usted no posee los permisos necesarios para realizar la acción solicitada"
                }));
        }
        sendEmail(emailObject);
    }

    private void simpleNotifySuccess(String email, String message) {
        Email emailObject = new Email(email, Email.SUBJECT,
                HtmlBuilder.generateText(new String[]{
            "Petición realizada correctamente",
            message
        }));
        sendEmail(emailObject);
    }

    private void simpleNotify(String email, String title, String topic, String message) {
        Email emailObject = new Email(email, Email.SUBJECT,
                HtmlBuilder.generateText(new String[]{
            title, topic, message
        }));
        sendEmail(emailObject);
    }

    private void tableNotifySuccess(String email, String title, String[] headers, ArrayList<String[]> data) {
        Email emailObject = new Email(email, Email.SUBJECT,
                HtmlBuilder.generateTable(title, headers, data));
        sendEmail(emailObject);
    }

    private void simpleTableNotifySuccess(String email, String title, String[] headers, String[] data) {
        Email emailObject = new Email(email, Email.SUBJECT,
                HtmlBuilder.generateTableForSimpleData(title, headers, data));
        sendEmail(emailObject);
    }

    private void sendEmail(Email email) {
        SendEmailThread sendEmail = new SendEmailThread(email);
        Thread thread = new Thread(sendEmail);
        thread.setName("Send email Thread");
        thread.start();
    }
}
