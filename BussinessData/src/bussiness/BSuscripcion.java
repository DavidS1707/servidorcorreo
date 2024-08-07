/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bussiness;

import data.DSuscripcion;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suarez
 */
public class BSuscripcion {

    private DSuscripcion dSuscripcion;

    public BSuscripcion() {
        dSuscripcion = new DSuscripcion();
    }

    public void create(List<String> parametros) throws SQLException, ParseException {
        dSuscripcion.create(parametros.get(0), Integer.parseInt(parametros.get(1)), Integer.parseInt(parametros.get(2)), parametros.get(3));
        dSuscripcion.disconnect();
    }

    public void edit(List<String> parametros) throws SQLException {
        dSuscripcion.edit(Integer.parseInt(parametros.get(0)), parametros.get(1), Integer.parseInt(parametros.get(2)), Integer.parseInt(parametros.get(3)), parametros.get(4));
        dSuscripcion.disconnect();
    }

    public void delete(List<String> parametros) throws SQLException {
        dSuscripcion.delete(Integer.parseInt(parametros.get(0)));
        dSuscripcion.disconnect();
    }

    public List<String[]> show() throws SQLException {
        ArrayList<String[]> proyectos = (ArrayList<String[]>) dSuscripcion.show();
        dSuscripcion.disconnect();
        return proyectos;
    }
}
