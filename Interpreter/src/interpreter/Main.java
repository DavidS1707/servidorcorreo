/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interpreter;

import interpreter.analex.Interpreter;
import interpreter.analex.interfaces.ITokenEventListener;
import interpreter.analex.utils.Token;
import interpreter.events.TokenEvent;
import java.sql.SQLException;
import java.util.ArrayList;

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
        String comando = "usuario get ";
        String correo = "ronaldorivero@uagrm.edu.bo";
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

        BProducto bProducto = new BProducto();
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
                    if (event.getAction() == Token.GET) {
                        ArrayList<String[]> lista = bUsuario.listar();

                        String s = "";
                        for (int i = 0; i < lista.size(); i++) {
                            s = s + "[" + i + "] : ";
                            for (int j = 0; j < lista.get(i).length; j++) {
                                s = s + lista.get(i)[j] + " | ";
                            }
                            s = s + "\n";
                        }
                        System.out.println(s);
                    }
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                }
            }

            @Override
            public void producto(TokenEvent event) {
                System.out.println("CU: MASCOTA");
                System.out.println(event);
                try {
                    if (event.getAction() == Token.AGREGAR) {
                        bProducto.guardar(event.getParams(), event.getSender());
                        System.out.println("OK");
                        //notificar al usuario que se ejecuto su comando
                    } else if (event.getAction() == Token.MODIFY) {

                    } else if (event.getAction() == Token.DELETE) {

                    } else {
                        System.out.println("La accion no es valida para el caso de uso");
                        //enviar al correo una notificacion
                    }
                } catch (SQLException ex) {
                    System.out.println("Mensaje: " + ex.getSQLState());
                    //enviar notificacion de error
                }
            }

            @Override
            public void error(TokenEvent event) {
                System.out.println("DESCONOCIDO");
                System.out.println(event);
                //enviar una notificacion
            }
        });

        Thread thread = new Thread(interpreter);
        thread.setName("Interpreter Thread");
        thread.start();
    }

}
