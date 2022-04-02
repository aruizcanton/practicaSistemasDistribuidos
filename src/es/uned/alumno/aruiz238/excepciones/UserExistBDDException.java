package es.uned.alumno.aruiz238.excepciones;

public class UserExistBDDException extends Exception{
    public UserExistBDDException () {}
    public UserExistBDDException (String msg) {
        super (msg);
    }
}
