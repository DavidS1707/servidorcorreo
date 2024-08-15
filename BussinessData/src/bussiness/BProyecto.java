/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bussiness;

import data.DProyecto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suarez
 */
public class BProyecto {

    private DProyecto dProyecto;

    public BProyecto() {
        dProyecto = new DProyecto();
    }

    public void create(List<String> parametros) throws SQLException {
        dProyecto.create(parametros.get(0), parametros.get(1), parametros.get(2), parametros.get(3));
        dProyecto.disconnect();
    }

    public void edit(List<String> parametros) throws SQLException {
        dProyecto.edit(Integer.parseInt(parametros.get(0)), parametros.get(1), parametros.get(2), parametros.get(3), parametros.get(4), parametros.get(5));
        dProyecto.disconnect();
    }

    public void delete(List<String> parametros) throws SQLException {
        dProyecto.delete(Integer.parseInt(parametros.get(0)));
        dProyecto.disconnect();
    }

    public List<String[]> show() throws SQLException {
        ArrayList<String[]> proyectos = (ArrayList<String[]>) dProyecto.show();
        dProyecto.disconnect();
        return proyectos;
    }
}
