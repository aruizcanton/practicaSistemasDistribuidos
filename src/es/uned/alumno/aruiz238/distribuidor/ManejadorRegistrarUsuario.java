package es.uned.alumno.aruiz238.distribuidor;

import es.uned.alumno.aruiz238.excepciones.UserExistBDDException;
import es.uned.alumno.aruiz238.excepciones.UserPassFormatException;
import es.uned.alumno.aruiz238.modelo.EspecUsuario;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ManejadorRegistrarUsuario {
    public ManejadorRegistrarUsuario() {}
    public EspecUsuario registarUsuario (String userName, String password) throws NotBoundException, UserPassFormatException, RemoteException, UserExistBDDException {
        final EspecUsuario especUsuario = new EspecUsuario (userName, password);
        especUsuario.registrarse();
        return especUsuario;
    }
}
