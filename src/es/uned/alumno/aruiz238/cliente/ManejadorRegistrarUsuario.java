/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.cliente;

import es.uned.alumno.aruiz238.excepciones.UserExistBDDException;
import es.uned.alumno.aruiz238.excepciones.UserPassFormatException;
import es.uned.alumno.aruiz238.modelo.EspecUsuario;
import es.uned.alumno.aruiz238.modelo.TipoUsuEnum;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ManejadorRegistrarUsuario {
    public ManejadorRegistrarUsuario() {}
    public EspecUsuario registarUsuario (String userName, String password) throws NotBoundException, UserPassFormatException, RemoteException, UserExistBDDException {
        final EspecUsuario especUsuario = new EspecUsuario (userName, password, TipoUsuEnum.CLIENTE);
        especUsuario.registrarse();
        return especUsuario;
    }
}
