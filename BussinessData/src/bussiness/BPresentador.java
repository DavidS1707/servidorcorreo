/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bussiness;

import data.DPresentador;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suarez
 */
public class BPresentador {

    private DPresentador dPresentador;

    public BPresentador() {
        dPresentador = new DPresentador();
    }

    public void create(List<String> parametros) throws SQLException {
        dPresentador.create(parametros.get(0), parametros.get(1), parametros.get(2));
        dPresentador.disconnect();
    }

    public void edit(List<String> parametros) throws SQLException {
        dPresentador.edit(Integer.parseInt(parametros.get(0)), parametros.get(1), parametros.get(2), parametros.get(3));
        dPresentador.disconnect();
    }

    public void delete(List<String> parametros) throws SQLException {
        dPresentador.delete(Integer.parseInt(parametros.get(0)));
        dPresentador.disconnect();
    }

    public List<String[]> show() throws SQLException {
        ArrayList<String[]> presentador = (ArrayList<String[]>) dPresentador.show();
        dPresentador.disconnect();
        return presentador;
    }
}
