/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.util.List;
import utils.Email;

/**
 *
 * @author suarez
 */
public interface IEmailEventListener {

    void onReceiveEmailEvent(List<Email> emails);
}
