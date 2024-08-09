/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bussiness;

import data.DElemento;
import data.DUsuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suarez
 */
public class BContenido {

    private DElemento dElemento;

    public BContenido() {
        dElemento = new DElemento();
    }

    public void create(List<String> parametros) throws SQLException {
        dElemento.create(parametros.get(0), parametros.get(1), parametros.get(2));
        dElemento.disconnect();
    }

    public void edit(List<String> parametros) throws SQLException {
        dElemento.edit(Integer.parseInt(parametros.get(0)), parametros.get(1), parametros.get(2), parametros.get(3));
        dElemento.disconnect();
    }

    public void delete(List<String> parametros) throws SQLException {
        dElemento.delete(Integer.parseInt(parametros.get(0)));
        dElemento.disconnect();
    }

    public List<String[]> show() throws SQLException {
        ArrayList<String[]> users = (ArrayList<String[]>) dElemento.show();
        dElemento.disconnect();
        return users;
    }
}
