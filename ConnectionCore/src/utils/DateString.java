/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author suarez
 */
public class DateString {

    //metodo para convertir un string en una formato de fecha
    public static Calendar StringToDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Calendar dt = Calendar.getInstance();
            dt.setTime(format.parse(date));
            return dt;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //metodo para convertir una fecha en string
    public static String DateToString(Calendar date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String dt = format.format(date);
            return dt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //metodo para convertir un string en una formato de fecha y hora
    public static Calendar StringToDateTime(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Calendar dt = Calendar.getInstance();
            dt.setTime(format.parse(date));
            return dt;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //metodo para convertir una fecha y hora en un string
    public static String DateTimeToString(Calendar date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String dt = format.format(date);
            return dt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //metodo para convertir un string en una fechaSQL
    public static Date StringToDateSQL(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date(format.parse(date).getTime());
        return dt;
    }

    //metodo para convertir una fechaSQL en un string
    public static String DateSQLToString(Date date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dt = format.format(date);
        return dt;
    }

    //metodo para convertir un string en una fecha y hora SQL
    public static Date StringToDateTimeSQL(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp dt = new Timestamp(format.parse(date).getTime());
        return dt;
    }

    //metodo para convertir una fecha y hora SQL en un string
    public static String DateTimeSQLToString(Timestamp date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dt = format.format(date);
        return dt;
    }
}
