/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import Interfaces.IEmailEventListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.sasl.AuthenticationException;
import utils.Command;
import utils.Email;
import utils.Extractor;

/**
 *
 * @author suarez
 */
public class MailVerificationThread implements Runnable {

    //CREDENCIALES PARA EL SERVIDOR DE CORREO ESPECIFICO
    private final static int PORT_POP = 110;
    private final static String HOST = "mail.tecnoweb.org.bo";
    private final static String USER = "grupo23sa";
    private final static String PASSWORD = "grup023grup023*";

    private Socket socket;
    private BufferedReader input;
    private DataOutputStream output;
    private IEmailEventListener emailEventListener;

    public IEmailEventListener getEmailEventListener() {
        return emailEventListener;
    }

    public void setEmailEventListener(IEmailEventListener emailEventListener) {
        this.emailEventListener = emailEventListener;
    }

    public MailVerificationThread() {
        socket = null;
        input = null;
        output = null;
    }

    @Override
    public void run() {
        while (true) {
            try {
                List<Email> emails = null;
                socket = new Socket(HOST, PORT_POP);
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new DataOutputStream(socket.getOutputStream());
                System.out.print("*************** Conexion Establecida *************** \n");
                authUser(USER, PASSWORD);
                output.writeBytes(Command.stat());
                int count = getEmailCount();
                if (count > 0) {
                    emails = getEmails(count);
                    System.out.print(emails.toString());
                    deleteEmails(count);
                }
                output.writeBytes(Command.quit());
                input.readLine();
                input.close();
                output.close();
                socket.close();
                System.out.print("*************** Conexion Terminada *************** \n");
                if (count > 0) {
                    emailEventListener.onReceiveEmailEvent(emails);
                }
                Thread.sleep(10000);
            } catch (IOException ex) {
                Logger.getLogger(MailVerificationThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(MailVerificationThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void authUser(String email, String password) throws IOException {
        if (socket != null && input != null && output != null) {
            input.readLine();
            output.writeBytes(Command.user(email));
            input.readLine();
            output.writeBytes(Command.pass(password));
            String message = input.readLine();
            if (message.contains("-ERR")) {
                throw new AuthenticationException();
            }
        }
    }

    private void deleteEmails(int emails) throws IOException {
        for (int i = 1; i <= emails; i++) {
            output.writeBytes(Command.dele(i));
        }
    }

    private int getEmailCount() throws IOException {
        output.writeBytes(Command.stat());
        String line = input.readLine();
        String[] data = line.split(" ");
        return Integer.parseInt(data[1]);
    }

    private List<Email> getEmails(int count) throws IOException {
        List<Email> emails = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            output.writeBytes(Command.retr(i));
            String text = readMultiline();
            emails.add(Extractor.getEmail(text));
        }
        return emails;
    }

    private String readMultiline() throws IOException {
        String lines = "";
        while (true) {
            String line = input.readLine();
            if (line == null) {
                throw new IOException("Server no responde. Error al abrir correo.");
            }
            if (line.equals(".")) {
                break;
            }
            lines = lines + "\n" + line;
        }
        return lines;
    }
}
