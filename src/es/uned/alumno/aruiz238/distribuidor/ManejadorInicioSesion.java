/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.distribuidor;

import es.uned.alumno.aruiz238.excepciones.PasswordIncorrectException;
import es.uned.alumno.aruiz238.excepciones.UserNameNotRegistredException;
import es.uned.alumno.aruiz238.excepciones.UserPassFormatException;
import es.uned.alumno.aruiz238.modelo.EspecUsuario;
import es.uned.alumno.aruiz238.modelo.TipoUsuEnum;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ManejadorInicioSesion {
    public ManejadorInicioSesion () {}
    public EspecUsuario IniciarSesion (String userName, String password) throws UserNameNotRegistredException, NotBoundException, UserPassFormatException, PasswordIncorrectException, RemoteException {
        final EspecUsuario especUsuario = new EspecUsuario (userName, password, TipoUsuEnum.DISTRIBUIDOR);
        especUsuario.iniciarSesion();
        return especUsuario;
    }
}
