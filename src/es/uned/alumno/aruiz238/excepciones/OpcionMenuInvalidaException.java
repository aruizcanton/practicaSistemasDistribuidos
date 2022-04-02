//////////////////////////////////////////
// Autor: Ángel Ruiz Cantón
// E-mail: aruiz238@alumno.uned.es
//////////////////////////////////////////
package es.uned.alumno.aruiz238.excepciones;

public class OpcionMenuInvalidaException extends Exception {
    public OpcionMenuInvalidaException(){};
    public OpcionMenuInvalidaException (String msg) {
        super(msg);
    }
}
