/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemacentral;

import Interfaces.IEmailEventListener;
import bussiness.BUsuario;
import communication.MailVerificationThread;
import communication.SendEmailThread;
import data.DUsuario;
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
import bussiness.BContenido;
import bussiness.BEstadistica;
import bussiness.BNoticia;
import bussiness.BPago;
import bussiness.BPresentador;
import bussiness.BProyecto;
import bussiness.BSuscripcion;
import interpreter.Main;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author suarez
 */
public class MailApplication {

    private static final int CONSTRAINTS_ERROR = -2;
    private static final int NUMBER_FORMAT_ERROR = -3;
    private static final int INDEX_OUT_OF_BOUND_ERROR = -4;
    private static final int PARSE_ERROR = -5;
    private static final int AUTHORIZATION_ERROR = -6;

    private MailVerificationThread mailVerificationThread;
    private BUsuario bUsuario;
    private BContenido bContenido;
    private BEstadistica bEstadistica;
    private BNoticia bNoticia;
    private BPago bPago;
    private BPresentador bPresentador;
    private BProyecto bProyecto;
    private BSuscripcion bSuscripcion;

    public MailApplication() {
        mailVerificationThread = new MailVerificationThread();
        mailVerificationThread.setEmailEventListener((IEmailEventListener) MailApplication.this);
        bUsuario = new BUsuario();
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

    public void onReceiveEmailEvent(List<Email> emails) {
        for (Email email : emails) {
            Interpreter interpreter = new Interpreter(email.getSubject(), email.getFrom());
            interpreter.setListener((ITokenEventListener) MailApplication.this);
            Thread thread = new Thread(interpreter);
            thread.setName("Interpreter Thread");
            thread.start();
        }
    }

    public void usuario(TokenEvent event) {
        System.out.println("CU: USUARIO");
        System.out.println(event);
        try {
            if (event.getAction() == Token.AGREGAR) {
                bUsuario.create(event.getParams());
                System.out.println("Usuario registrado correctamente!");
            } else if (event.getAction() == Token.MODIFY) {
                bUsuario.edit(event.getParams());
                System.out.println("Usuario modificado correctamente!");
            } else if (event.getAction() == Token.GET) {
                ArrayList<String[]> lista = (ArrayList<String[]>) bUsuario.show();

                String s = "";
                for (int i = 0; i < lista.size(); i++) {
                    s = s + "[" + i + "] : ";
                    for (int j = 0; j < lista.get(i).length; j++) {
                        s = s + lista.get(i)[j] + " | ";
                    }
                    s = s + "\n";
                }
                System.out.println(s);
            } else if (event.getAction() == Token.DELETE) {
                bUsuario.delete(event.getParams());
                System.out.println("Usuario eliminado correctamente!");
            } else {
                System.out.println("La accion no es valida para el caso de uso");
                //enviar al correo una notificacion
            } //enviar notificacion de error
        } catch (SQLException ex) {
            System.out.println("Mensaje: " + ex.getSQLState());
            //enviar notificacion de error
        }
    }

    public void estadistica(TokenEvent event) throws SQLException {
        System.out.println("CU: ESTADISTICA");
        System.out.println(event);
        if (event.getAction() == Token.GET) {
            ArrayList<String[]> lista = (ArrayList<String[]>) bEstadistica.showNoticias();

            String s = "";
            for (int i = 0; i < lista.size(); i++) {
                s = s + "[" + i + "] : ";
                for (int j = 0; j < lista.get(i).length; j++) {
                    s = s + lista.get(i)[j] + " | ";
                }
                s = s + "\n";
            }
            System.out.println(s);
        } else {
            System.out.println("La accion no es valida para el caso de uso");
            //enviar al correo una notificacion
        } //enviar notificacion de error
        //enviar notificacion de error
    }

    public void noticia(TokenEvent event) throws SQLException, ParseException {
        System.out.println("CU: NOTICIA");
        System.out.println(event);
        if (event.getAction() == Token.AGREGAR) {
            bNoticia.create(event.getParams());
            System.out.println("Noticia registrada correctamente!");
        } else if (event.getAction() == Token.MODIFY) {
            bNoticia.edit(event.getParams());
            System.out.println("Noticia modificada correctamente!");
        } else if (event.getAction() == Token.GET) {
            ArrayList<String[]> lista = (ArrayList<String[]>) bNoticia.show();

            String s = "";
            for (int i = 0; i < lista.size(); i++) {
                s = s + "[" + i + "] : ";
                for (int j = 0; j < lista.get(i).length; j++) {
                    s = s + lista.get(i)[j] + " | ";
                }
                s = s + "\n";
            }
            System.out.println(s);
        } else if (event.getAction() == Token.DELETE) {
            bNoticia.delete(event.getParams());
            System.out.println("Noticia eliminada correctamente!");
        } else {
            System.out.println("La accion no es valida para el caso de uso");
            //enviar al correo una notificacion
        } //enviar notificacion de error
//enviar notificacion de error

    }

    public void pago(TokenEvent event) throws SQLException {
        System.out.println("CU: PAGO");
        System.out.println(event);
        if (event.getAction() == Token.AGREGAR) {
            bPago.create(event.getParams());
            System.out.println("Pago registrado correctamente!");
        } else if (event.getAction() == Token.MODIFY) {
            bPago.edit(event.getParams());
            System.out.println("Pago modificado correctamente!");
        } else if (event.getAction() == Token.GET) {
            ArrayList<String[]> lista = (ArrayList<String[]>) bPago.show();

            String s = "";
            for (int i = 0; i < lista.size(); i++) {
                s = s + "[" + i + "] : ";
                for (int j = 0; j < lista.get(i).length; j++) {
                    s = s + lista.get(i)[j] + " | ";
                }
                s = s + "\n";
            }
            System.out.println(s);
        } else if (event.getAction() == Token.DELETE) {
            bPago.delete(event.getParams());
            System.out.println("Pago eliminado correctamente!");
        } else {
            System.out.println("La accion no es valida para el caso de uso");
            //enviar al correo una notificacion
        } //enviar notificacion de error
//enviar notificacion de error
    }

    public void presentador(TokenEvent event) throws SQLException {
        System.out.println("CU: PRESENTADOR");
        System.out.println(event);
        if (event.getAction() == Token.AGREGAR) {
            bPresentador.create(event.getParams());
            System.out.println("Presentador registrado correctamente!");
        } else if (event.getAction() == Token.MODIFY) {
            bPresentador.edit(event.getParams());
            System.out.println("Presentador modificado correctamente!");
        } else if (event.getAction() == Token.GET) {
            ArrayList<String[]> lista = (ArrayList<String[]>) bPresentador.show();

            String s = "";
            for (int i = 0; i < lista.size(); i++) {
                s = s + "[" + i + "] : ";
                for (int j = 0; j < lista.get(i).length; j++) {
                    s = s + lista.get(i)[j] + " | ";
                }
                s = s + "\n";
            }
            System.out.println(s);
        } else if (event.getAction() == Token.DELETE) {
            bPresentador.delete(event.getParams());
            System.out.println("Presentador eliminado correctamente!");
        } else {
            System.out.println("La accion no es valida para el caso de uso");
            //enviar al correo una notificacion
        } //enviar notificacion de error
//enviar notificacion de error
    }

    public void proyecto(TokenEvent event) throws SQLException {
        System.out.println("CU: PROYECTO");
        System.out.println(event);
        if (event.getAction() == Token.AGREGAR) {
            bProyecto.create(event.getParams());
            System.out.println("Proyecto registrado correctamente!");
        } else if (event.getAction() == Token.MODIFY) {
            bProyecto.edit(event.getParams());
            System.out.println("Proyecto modificado correctamente!");
        } else if (event.getAction() == Token.GET) {
            ArrayList<String[]> lista = (ArrayList<String[]>) bProyecto.show();

            String s = "";
            for (int i = 0; i < lista.size(); i++) {
                s = s + "[" + i + "] : ";
                for (int j = 0; j < lista.get(i).length; j++) {
                    s = s + lista.get(i)[j] + " | ";
                }
                s = s + "\n";
            }
            System.out.println(s);
        } else if (event.getAction() == Token.DELETE) {
            bProyecto.delete(event.getParams());
            System.out.println("Proyecto eliminado correctamente!");
        } else {
            System.out.println("La accion no es valida para el caso de uso");
            //enviar al correo una notificacion
        } //enviar notificacion de error
//enviar notificacion de error
    }

    public void error(TokenEvent event) {
        handleError(event.getAction(), event.getSender(), event.getParams());
    }

    private void handleError(int type, String email, List<String> args) {
        Email emailObject = null;

        switch (type) {
            case Token.ERROR_CHARACTER:
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Caracter desconocido",
                    "No se pudo ejecutar el comando [" + args.get(0) + "] debido a: ",
                    "El caracter \"" + args.get(1) + "\" es desconocido."
                }));
                break;
            case Token.ERROR_COMMAND:
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Comando desconocido",
                    "No se pudo ejecutar el comando [" + args.get(0) + "] debido a: ",
                    "No se reconoce la palabra \"" + args.get(1) + "\" como un comando válido"
                }));
                break;
            case CONSTRAINTS_ERROR:
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Error al interactuar con la base de datos",
                    "Referencia a información inexistente"
                }));
                break;
            case NUMBER_FORMAT_ERROR:
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Error en el tipo de parámetro",
                    "El tipo de uno de los parámetros es incorrecto"
                }));
                break;
            case INDEX_OUT_OF_BOUND_ERROR:
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Cantidad de parámetros incorrecta",
                    "La cantidad de parámetros para realizar la acción es incorrecta"
                }));
                break;
            case PARSE_ERROR:
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Error al procesar la fecha",
                    "La fecha introducida posee un formato incorrecto"
                }));
                break;
            case AUTHORIZATION_ERROR:
                emailObject = new Email(email, Email.SUBJECT,
                        HtmlBuilder.generateText(new String[]{
                    "Acceso denegado",
                    "Usted no posee los permisos necesarios para realizar la acción solicitada"
                }));
                break;
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
