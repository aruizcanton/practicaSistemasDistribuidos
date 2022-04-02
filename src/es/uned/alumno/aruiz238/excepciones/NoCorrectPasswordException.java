package es.uned.alumno.aruiz238.excepciones;

public class NoCorrectPasswordException extends Exception{
    public NoCorrectPasswordException(){}
    public NoCorrectPasswordException (String msg) {
        super(msg);
    }
}
