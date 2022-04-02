//////////////////////////////////////////
// Autor: Ángel Ruiz Cantón
// E-mail: aruiz238@alumno.uned.es
//////////////////////////////////////////

package es.uned.alumno.aruiz238.regulador;

import es.uned.alumno.aruiz238.excepciones.PasswordIncorrectException;
import es.uned.alumno.aruiz238.excepciones.UserExistBDDException;
import es.uned.alumno.aruiz238.excepciones.UserNameNotRegistredException;
import es.uned.alumno.aruiz238.excepciones.UserPassFormatException;
import es.uned.alumno.aruiz238.modelo.EspecUsuario;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import static es.uned.alumno.aruiz238.comm.IniciarRMI.registryHost;
import static es.uned.alumno.aruiz238.comm.IniciarRMI.registryPort;

public interface ServicioAutenticacionInterface extends Remote {
    public static final int idProveedorServAutenticacion = 2;
    public static final String NOMBRE_SERVICIO_AUTENTICACION = "rmi://" + registryHost + ":" + registryPort + "/servicioautenticacion/" + idProveedorServAutenticacion;

    EspecUsuario registrarUsuario (String userName, String password) throws RemoteException, UserPassFormatException
            , NotBoundException, UserExistBDDException;
    EspecUsuario iniciarSesion (String userName, String password) throws RemoteException, NotBoundException
            , UserPassFormatException, UserNameNotRegistredException, PasswordIncorrectException;
    void bajaUsuario (EspecUsuario especUsuario) throws RemoteException, NotBoundException;
}
