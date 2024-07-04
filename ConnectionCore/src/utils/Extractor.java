/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author suarez
 */
public class Extractor {

    private static String GMAIL = "d=gmail";
    private static String HOTMAIL = "d=hotmail";
    private static String YAHOO = "d=yahoo";

    public static Email getEmail(String plain_text) {
        return new Email(getFrom(plain_text), getSubject(plain_text));
    }

    public static String getFrom(String plain_text) {
        String to = "";
        if (plain_text.contains(GMAIL)) {
            to = getToFromGmail(plain_text);
        } else if (plain_text.contains(HOTMAIL)) {
            to = getToFromHotmail(plain_text);
        } else if (plain_text.contains(YAHOO)) {
            to = getToFromYahoo(plain_text);
        }
        return to;
    }

    public static String getSubject(String plain_text) {
        String search = "Subject: ";
        int i = plain_text.indexOf(search) + search.length();
        String end_string = "";
        if (plain_text.contains(GMAIL)) {
            end_string = "To:";
        } else if (plain_text.contains(HOTMAIL)) {
            end_string = "Thread-Topic";
        } else if (plain_text.contains(YAHOO)) {
            end_string = "MIME-version";
        }
        int e = plain_text.indexOf(end_string, i);
        return plain_text.substring(i, e);
    }

    public static String getToFromGmail(String plain_text) {
        return getToCommon(plain_text);
    }

    public static String getToFromHotmail(String plain_text) {
        String aux = getToCommon(plain_text);
        return aux.substring(1, aux.length() - 1);

    }

    public static String getToFromYahoo(String plain_text) {
        int index = plain_text.indexOf("To: ");
        int i = plain_text.indexOf("<", index);
        int e = plain_text.indexOf(">", i);
        return plain_text.substring(i + 1, e);
    }

    public static String getToCommon(String plain_text) {
        String aux = "To: ";
        int index_begin = plain_text.indexOf(aux) + aux.length();
        int index_end = plain_text.indexOf("\n" + index_begin);
        return plain_text.substring(index_end, index_end);
    }
}
