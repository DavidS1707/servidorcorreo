/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connectioncore;

import Interfaces.IEmailEventListener;
import communication.MailVerificationThread;
import java.util.List;
import utils.Email;

/**
 *
 * @author suarez
 */
public class ConnectionCore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MailVerificationThread mail = new MailVerificationThread();
        mail.setEmailEventListener(new IEmailEventListener() {
            @Override
            public void onReceiveEmailEvent(List<Email> emails) {
                for (Email email : emails) {
                    System.out.println(email);
                }
            }
        });

        Thread thread = new Thread(mail);
        thread.setName("Mail Verification Thread");
        thread.start();
    }

}
