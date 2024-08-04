/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bussiness;

import data.DComando;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author suarez
 */
public class BComando {

    private DComando dComando;

    public BComando() {
        this.dComando = new DComando();
    }

    public ArrayList<String[]> listar() throws SQLException {
        ArrayList<String[]> comandos = (ArrayList<String[]>) dComando.listar();
        dComando.desconectar();
        return comandos;
    }
}
