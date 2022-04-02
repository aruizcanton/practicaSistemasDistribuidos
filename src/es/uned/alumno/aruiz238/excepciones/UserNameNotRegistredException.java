package es.uned.alumno.aruiz238.excepciones;

public class UserNameNotRegistredException extends Exception{
    public UserNameNotRegistredException() {}
    public UserNameNotRegistredException (String msg) {
        super(msg);
    }
}
