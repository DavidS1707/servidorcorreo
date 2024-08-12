/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bussiness;

import data.DUsuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suarez
 */
public class BUsuario {

    private DUsuario dUsuario;

    public BUsuario() {
        dUsuario = new DUsuario();
    }

    public void create(List<String> parametros) throws SQLException {
        dUsuario.create(parametros.get(0), parametros.get(1), parametros.get(2), 20);
        dUsuario.disconnect();
    }

    public void edit(List<String> parametros) throws SQLException {
        dUsuario.edit(Integer.parseInt(parametros.get(0)), parametros.get(1), parametros.get(2), parametros.get(3), Integer.parseInt(parametros.get(4)));
        dUsuario.disconnect();
    }

    public void delete(List<String> parametros) throws SQLException {
        dUsuario.delete(Integer.parseInt(parametros.get(0)));
        dUsuario.disconnect();
    }

    public List<String[]> show() throws SQLException {
        ArrayList<String[]> users = (ArrayList<String[]>) dUsuario.show();
        dUsuario.disconnect();
        return users;
    }
}
