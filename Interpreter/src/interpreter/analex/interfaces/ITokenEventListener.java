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

    void user(TokenEvent event);

    void client(TokenEvent event);

    void dpto(TokenEvent event);

    void social(TokenEvent event);

    void schedule(TokenEvent event);

    void notify(TokenEvent event);

    void apartment(TokenEvent event);

    void visit(TokenEvent event);

    void support(TokenEvent event);

    void reserve(TokenEvent event);

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
}
