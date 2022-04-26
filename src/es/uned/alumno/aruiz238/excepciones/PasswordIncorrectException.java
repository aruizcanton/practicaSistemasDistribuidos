/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.excepciones;

public class PasswordIncorrectException extends Exception {
    public PasswordIncorrectException () {}
    public PasswordIncorrectException (String msg) {
        super(msg);
    }
}
