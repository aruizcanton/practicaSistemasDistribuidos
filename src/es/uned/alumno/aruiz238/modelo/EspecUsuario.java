/*
////////////////////////////////////////
 Autor: Ángel Ruiz Cantón
 E-mail: aruiz238@alumno.uned.es
////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.modelo;

import es.uned.alumno.aruiz238.excepciones.PasswordIncorrectException;
import es.uned.alumno.aruiz238.excepciones.UserExistBDDException;
import es.uned.alumno.aruiz238.excepciones.UserNameNotRegistredException;
import es.uned.alumno.aruiz238.excepciones.UserPassFormatException;
import es.uned.alumno.aruiz238.interfaz.FactoriaDeServicios;
import es.uned.alumno.aruiz238.interfaz.IAdaptadorServAutenticacion;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class EspecUsuario implements Serializable {
    private String userName;
    private String password;
    private UsuarioId userId;

    public EspecUsuario(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public UsuarioId getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(UsuarioId userId) {
        this.userId = userId;
    }

    public void darseDeBaja () throws NotBoundException, RemoteException {
        final IAdaptadorServAutenticacion servAu = FactoriaDeServicios.getInstancia().getAdaptadorServAutenticacion();
        servAu.removeUsuario (this);
    }
    public void registrarse () throws NotBoundException, RemoteException, UserPassFormatException, UserExistBDDException {
        final IAdaptadorServAutenticacion servAu = FactoriaDeServicios.getInstancia ().getAdaptadorServAutenticacion ();
        this.userId = servAu.registrarUsuario (this.userName, this.password);
    }
    public void iniciarSesion() throws NotBoundException, RemoteException, UserPassFormatException, UserNameNotRegistredException, PasswordIncorrectException {
        final IAdaptadorServAutenticacion servAu = FactoriaDeServicios.getInstancia ().getAdaptadorServAutenticacion ();
        this.userId = servAu.loginUsuario (this.userName, this.password);
    }
}
