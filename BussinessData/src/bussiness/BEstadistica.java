/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bussiness;

import data.DNoticia;
import data.DPresentador;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suarez
 */
public class BEstadistica {

    private DNoticia dNoticia;
    private DPresentador dPresentador;

    public BEstadistica() {
        dNoticia = new DNoticia();
        dPresentador = new DPresentador();
    }

    public List<String[]> showNoticias() throws SQLException {
        ArrayList<String[]> noticias = (ArrayList<String[]>) dNoticia.show();
        dNoticia.disconnect();
        return noticias;
    }

    public List<String[]> showPresentador() throws SQLException {
        ArrayList<String[]> presentadores = (ArrayList<String[]>) dPresentador.show();
        dNoticia.disconnect();
        return presentadores;
    }
}
