//////////////////////////////////////////
// Autor: Ángel Ruiz Cantón
// E-mail: aruiz238@alumno.uned.es
//////////////////////////////////////////

package es.uned.alumno.aruiz238.regulador;

import es.uned.alumno.aruiz238.comm.IniciarRMI;
import es.uned.alumno.aruiz238.excepciones.PasswordIncorrectException;
import es.uned.alumno.aruiz238.excepciones.UserExistBDDException;
import es.uned.alumno.aruiz238.excepciones.UserNameNotRegistredException;
import es.uned.alumno.aruiz238.excepciones.UserPassFormatException;
import es.uned.alumno.aruiz238.modelo.EspecUsuario;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Objects;


public class ServicioAutenticacionImpl implements ServicioAutenticacionInterface {

    @Override
    public EspecUsuario registrarUsuario (String userName, String password) throws RemoteException, UserPassFormatException
            , NotBoundException, UserExistBDDException {
            final Registry registry = LocateRegistry.getRegistry(IniciarRMI.registryPort);
            ServicioDatosInterface servDatos = (ServicioDatosInterface) registry.lookup (ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
            if (!Objects.equals(userName, "") && !Objects.equals(password, "")) {
                final EspecUsuario especUsuarioTmp = servDatos.getUsuBDD(userName);  // Comprobamos que el usuario efectivamente no existe en la BDD
                if (especUsuarioTmp == null) {
                    // Como no existe lo creamos
                    final EspecUsuario especUsuario = new EspecUsuario(userName, password);
                    return servDatos.anyadirUsuBDD(especUsuario);
                } else {
                    // El usuario ya se encuentra registrado en la BDD
                    UserExistBDDException e = new UserExistBDDException();
                    throw e;
                }
            } else {
                UserPassFormatException e = new UserPassFormatException();
                throw e;
            }
    }

    @Override
    public EspecUsuario iniciarSesion (String userName, String password) throws RemoteException, NotBoundException
            , UserPassFormatException, UserNameNotRegistredException, PasswordIncorrectException {
            final Registry registry = LocateRegistry.getRegistry(IniciarRMI.registryPort);
            ServicioDatosInterface servDatos = (ServicioDatosInterface) registry.lookup (ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
            if (!Objects.equals(userName, "") && !Objects.equals(password, "")) {
                final EspecUsuario especUsuario = servDatos.getUsuBDD(userName);
                if (especUsuario != null) {
                    if (especUsuario.getPassword().equals(password)) {
                        return especUsuario;
                    } else {
                        // La password introducida no coincide con la grabada en el sistema
                        PasswordIncorrectException e = new PasswordIncorrectException();
                        throw e;
                    }
                } else {
                    // El usuario no se encuentra registrado
                    UserNameNotRegistredException e = new UserNameNotRegistredException();
                    throw e;
                }
            } else {
                // El susuario y la password deben estar rellenas
                UserPassFormatException e = new UserPassFormatException();
                throw e;
            }
    }

    @Override
    public void bajaUsuario(EspecUsuario especUsuario) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(IniciarRMI.registryPort);
        ServicioDatosInterface servDatos = (ServicioDatosInterface) registry.lookup (ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
        servDatos.removeUsuBDD(especUsuario);
    }
}
