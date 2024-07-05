/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interpreter;

import bussiness.BUsuario;
import interpreter.analex.Interpreter;
import interpreter.analex.interfaces.ITokenEventListener;
import interpreter.analex.utils.Token;
import interpreter.events.TokenEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author suarez
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //AGREGAR USUARIO [Ronaldo, Rivero Gonzales, 76042142]
        //Nombre del caso uso | accion | parametros
        //[mascota|add|Firulay|25|Negro]
        //mascota add 
        //String comando = "producto agregar [Producto 200; 125]";
        //COMANDO PARA AGREGAR UN USUARIO
        //String comando = "usuario agregar [Pedro; Martinez; pedrito@gmail.com; 123456; 30; 2]";
        //COMANDO PARA VER LOS USUARIOS
        String comando = "usuario get ";
        String correo = "davsuar2000@gmail.com";
        /*
        //{Firulay, 25, Negro} //
        //CU: String - producto
        //ACTION: String - agregar
        //PARAMETROS: List<String> [Firulay,25,Negro]
        
        String CU = "producto";
        String ACTION = "agregar";
        List<String> PARAMETROS = new ArrayList<>();
        PARAMETROS.add("Producto 100"); PARAMETROS.add("25");
        
        BUsuario bUsuario = new BUsuario();
        BProducto bProducto = new BProducto();
            try {
                if(CU == "usuario") {
                    if(ACTION == "agregar") {

                    } else if(ACTION == "modificar") {

                    } else if(ACTION == "eliminar") {

                    } else if(ACTION == "listar") {

                    } else if(ACTION == "ver") {

                    }            
                } else if(CU == "producto") {
                    if(ACTION == "agregar") {                        
                        bProducto.guardar(PARAMETROS, correo);
                        System.out.println("Guardado del producto exitoso");
                        //enviar un correo de notificacion al usuario
                    } else if(ACTION == "modificar") {

                    } else if(ACTION == "eliminar") {

                    } else if(ACTION == "listar") {

                    } else if(ACTION == "ver") {

                    } else {
                        System.out.println("Comando no recorrido");
                        //enviar un correo notificando el error
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
         */

        //BProducto bProducto = new BProducto();
        BUsuario bUsuario = new BUsuario();

        Interpreter interpreter = new Interpreter(comando, correo);
        interpreter.setListener(new ITokenEventListener() {
            @Override
            public void user(TokenEvent event) {
                System.out.println("CU: USER");
                System.out.println(event);
            }

            @Override
            public void client(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void dpto(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void social(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void schedule(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void notify(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void apartment(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void visit(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void support(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void reserve(TokenEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void usuario(TokenEvent event) {
                System.out.println("CU: USUARIO");
                System.out.println(event);
                try {
                    if (event.getAction() == Token.AGREGAR) {
                        bUsuario.create(event.getParams());
                        System.out.println("Usuario registrado correctamente!");
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
                        //AGREGAR LOGICA PARA ELIMINAR
                    } else {
                        System.out.println("La accion no es valida para el caso de uso");
                        //enviar al correo una notificacion
                    } //enviar notificacion de error
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                }
            }

//            @Override
//            public void producto(TokenEvent event) {
//                System.out.println("CU: MASCOTA");
//                System.out.println(event);
//                if (event.getAction() == Token.AGREGAR) {
//                    bProducto.guardar(event.getParams(), event.getSender());
//                    System.out.println("OK");
//                    //notificar al usuario que se ejecuto su comando
//                } else if (event.getAction() == Token.MODIFY) {
//                    
//                } else if (event.getAction() == Token.DELETE) {
//                    
//                } else {
//                    System.out.println("La accion no es valida para el caso de uso");
//                    //enviar al correo una notificacion
//                } //enviar notificacion de error
//            }
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
        });

        Thread thread = new Thread(interpreter);
        thread.setName("Interpreter Thread");
        thread.start();
    }

}
