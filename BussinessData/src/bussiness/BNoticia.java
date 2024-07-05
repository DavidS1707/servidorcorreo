/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bussiness;

import data.DNoticia;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suarez
 */
public class BNoticia {

    private DNoticia dNoticia;

    public BNoticia() {
        dNoticia = new DNoticia();
    }

    public void create(List<String> parametros) throws SQLException, ParseException {
        dNoticia.create(parametros.get(0), parametros.get(1), parametros.get(2));
        dNoticia.disconnect();
    }

    public void edit(List<String> parametros) throws SQLException, ParseException {
        dNoticia.edit(Integer.parseInt(parametros.get(0)), parametros.get(1), parametros.get(2), parametros.get(3));
        dNoticia.disconnect();
    }

    public void delete(List<String> parametros) throws SQLException {
        dNoticia.delete(Integer.parseInt(parametros.get(0)));
        dNoticia.disconnect();
    }

    public List<String[]> show() throws SQLException {
        ArrayList<String[]> noticias = (ArrayList<String[]>) dNoticia.show();
        dNoticia.disconnect();
        return noticias;
    }
}
