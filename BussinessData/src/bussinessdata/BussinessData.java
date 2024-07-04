/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bussinessdata;

import bussiness.BUsuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author suarez
 */
public class BussinessData {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        user();
    }

    public static void user() {
        BUsuario bUsuario = new BUsuario();
        List<String> user = new ArrayList<String>();
        user.add("David");
        user.add("Suarez");
        user.add("davsuar2000@gmail.com");
        user.add("123456");
        user.add("30");
        user.add("1");
        try {
            bUsuario.create(user);
        } catch (SQLException ex) {
            Logger.getLogger(BussinessData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
