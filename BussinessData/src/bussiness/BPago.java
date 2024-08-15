/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bussiness;

import data.DPago;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suarez
 */
public class BPago {

    private final DPago dTipoPago;

    public BPago() {
        dTipoPago = new DPago();
    }

    public void create(List<String> parametros) throws SQLException, ParseException {
        dTipoPago.create(Integer.parseInt(parametros.get(0)), parametros.get(1), parametros.get(2), parametros.get(3));
        dTipoPago.disconnect();
    }

    public void edit(List<String> parametros) throws SQLException, ParseException {
        dTipoPago.edit(Integer.parseInt(parametros.get(0)), Integer.parseInt(parametros.get(1)), parametros.get(2), parametros.get(3), parametros.get(4));
        dTipoPago.disconnect();
    }

    public void delete(List<String> parametros) throws SQLException {
        dTipoPago.delete(Integer.parseInt(parametros.get(0)));
        dTipoPago.disconnect();
    }

    public List<String[]> show() throws SQLException {
        ArrayList<String[]> pagos = (ArrayList<String[]>) dTipoPago.show();
        dTipoPago.disconnect();
        return pagos;
    }
}
