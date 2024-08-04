/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interpreter.analex.interfaces;

import interpreter.events.TokenEvent;

/**
 *
 * @author suarez
 */
public interface ITokenEventListener {

    void usuario(TokenEvent event);

    void producto(TokenEvent event);

    void error(TokenEvent event);

    void contenido(TokenEvent event);

    void estadistica(TokenEvent event);

    void noticia(TokenEvent event);

    void pago(TokenEvent event);

    void presentador(TokenEvent event);

    void proyecto(TokenEvent event);

    void suscripcion(TokenEvent event);

    void help(TokenEvent token_event);
}
