package es.uned.alumno.aruiz238.interfaz;

import es.uned.alumno.aruiz238.excepciones.PasswordIncorrectException;
import es.uned.alumno.aruiz238.excepciones.UserExistBDDException;
import es.uned.alumno.aruiz238.excepciones.UserNameNotRegistredException;
import es.uned.alumno.aruiz238.excepciones.UserPassFormatException;
import es.uned.alumno.aruiz238.modelo.EspecUsuario;
import es.uned.alumno.aruiz238.modelo.UsuarioId;
import es.uned.alumno.aruiz238.regulador.ServicioAutenticacionInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static es.uned.alumno.aruiz238.comm.IniciarRMI.registryPort;

public class IAdaptadorServAutenticacion {
    public UsuarioId registrarUsuario (String userName, String password) throws RemoteException, NotBoundException, UserPassFormatException, UserExistBDDException {
        final Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioAutenticacionInterface servicioAutenticacionInterface = (ServicioAutenticacionInterface) registry.lookup(ServicioAutenticacionInterface.NOMBRE_SERVICIO_AUTENTICACION);
        final EspecUsuario especUsuario = servicioAutenticacionInterface.registrarUsuario (userName, password);
        return especUsuario.getUserId();
    }
    public UsuarioId loginUsuario (String userName, String password) throws RemoteException, NotBoundException, UserNameNotRegistredException, UserPassFormatException, PasswordIncorrectException {
        final Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioAutenticacionInterface servicioAutenticacionInterface = (ServicioAutenticacionInterface) registry.lookup(ServicioAutenticacionInterface.NOMBRE_SERVICIO_AUTENTICACION);
        final EspecUsuario especUsuario = servicioAutenticacionInterface.iniciarSesion (userName, password);
        return especUsuario.getUserId();
    }
    public void removeUsuario (EspecUsuario especUsuario) throws NotBoundException, RemoteException {
        final Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioAutenticacionInterface servicioAutenticacionInterface = (ServicioAutenticacionInterface) registry.lookup(ServicioAutenticacionInterface.NOMBRE_SERVICIO_AUTENTICACION);
        servicioAutenticacionInterface.bajaUsuario(especUsuario);

    }
}
