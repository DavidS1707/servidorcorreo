/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connectioncore;

import Interfaces.IEmailEventListener;
import bussiness.BContenido;
import bussiness.BEstadistica;
import bussiness.BNoticia;
import bussiness.BPago;
import bussiness.BPresentador;
import bussiness.BProyecto;
import bussiness.BSuscripcion;
import bussiness.BUsuario;
import communication.MailVerificationThread;
import interpreter.Main;
import interpreter.analex.Interpreter;
import interpreter.analex.interfaces.ITokenEventListener;
import interpreter.analex.utils.Token;
import interpreter.events.TokenEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Email;

/**
 *
 * @author suarez
 */
public class ConnectionCore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //PARA RECIBIR CORREOS
        MailVerificationThread mail = new MailVerificationThread();
        mail.setEmailEventListener(new IEmailEventListener() {
            @Override
            public void onReceiveEmailEvent(List<Email> emails) {
                for (Email email : emails) {
                    //System.out.println(email);
                    interpreter(email);
                }
            }
        });

        Thread thread = new Thread(mail);
        thread.setName("Mail Verification Thread");
        thread.start();

        //PARA ENVIAR CORREOS
//        Email emailObject = new Email("ronaldorivero3@gmail.com", Email.SUBJECT,
//                "Petición realizada correctamente");
//
//        SendEmailThread sendEmail = new SendEmailThread(emailObject);
//        Thread thread = new Thread(sendEmail);
//        thread.setName("Send email Thread");
//        thread.start();
    }

    public static void interpreter(Email email) {
        BUsuario bUsuario = new BUsuario();
        BContenido bContenido = new BContenido();
        BEstadistica bEstadistica = new BEstadistica();
        BNoticia bNoticia = new BNoticia();
        BPago bPago = new BPago();
        BPresentador bPresentador = new BPresentador();
        BProyecto bProyecto = new BProyecto();
        BSuscripcion bSuscripcion = new BSuscripcion();

        Interpreter interpreter = new Interpreter(email.getSubject(), email.getFrom());

        interpreter.setListener(new ITokenEventListener() {
            @Override
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

            @Override
            public void error(TokenEvent event) {
                System.out.println("ERROR DESCONOCIDO");
                System.out.println(event.getAction());
                System.out.println(event);
                //enviar una notificacion
            }

            @Override
            public void producto(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void contenido(TokenEvent event) {
                //
            }

            @Override
            public void estadistica(TokenEvent event) {
                System.out.println("CU: ESTADISTICA");
                System.out.println(event);
                try {
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
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                }
            }

            @Override
            public void noticia(TokenEvent event) {
                System.out.println("CU: NOTICIA");
                System.out.println(event);
                try {
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
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void pago(TokenEvent event) {
                System.out.println("CU: PAGO");
                System.out.println(event);
                try {
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
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                } catch (ParseException ex) {
                    Logger.getLogger(ConnectionCore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void presentador(TokenEvent event) {
                System.out.println("CU: PRESENTADOR");
                System.out.println(event);
                try {
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
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                }
            }

            @Override
            public void proyecto(TokenEvent event) {
                System.out.println("CU: PROYECTO");
                System.out.println(event);
                try {
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
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                }
            }

            @Override
            public void suscripcion(TokenEvent event) {
//                System.out.println("CU: SUSCRPCION");
//                System.out.println(event);
//                try {
//                    if (event.getAction() == Token.AGREGAR) {
//                        bSuscripcion.create(event.getParams());
//                        System.out.println("Usuario registrado correctamente!");
//                    } else if (event.getAction() == Token.MODIFY) {
//                        bSuscripcion.edit(event.getParams());
//                        System.out.println("Usuario modificado correctamente!");
//                    } else if (event.getAction() == Token.GET) {
//                        ArrayList<String[]> lista = (ArrayList<String[]>) bSuscripcion.show();
//
//                        String s = "";
//                        for (int i = 0; i < lista.size(); i++) {
//                            s = s + "[" + i + "] : ";
//                            for (int j = 0; j < lista.get(i).length; j++) {
//                                s = s + lista.get(i)[j] + " | ";
//                            }
//                            s = s + "\n";
//                        }
//                        System.out.println(s);
//                    } else if (event.getAction() == Token.DELETE) {
//                        bSuscripcion.delete(event.getParams());
//                        System.out.println("Usuario eliminado correctamente!");
//                    } else {
//                        System.out.println("La accion no es valida para el caso de uso");
//                        //enviar al correo una notificacion
//                    } //enviar notificacion de error
//                } catch (SQLException ex) {
//                    System.out.println("Mensaje: " + ex.getSQLState());
//                    //enviar notificacion de error
//                }
            }

            @Override
            public void help(TokenEvent token_event) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });

        Thread thread = new Thread(interpreter);
        thread.setName("Interpreter Thread");
        thread.start();
    }
}
