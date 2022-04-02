/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/

package es.uned.alumno.aruiz238.regulador;

import es.uned.alumno.aruiz238.modelo.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.ConcurrentHashMap;

import static es.uned.alumno.aruiz238.comm.IniciarRMI.registryHost;
import static es.uned.alumno.aruiz238.comm.IniciarRMI.registryPort;

public interface ServicioDatosInterface extends Remote {
    public static final int idProveedorServDatos = 1;
    public static final String NOMBRE_SERVICIO_DATOS = "rmi://" + registryHost + ":" + registryPort + "/serviciodatos/" + idProveedorServDatos;

    // Gestión de usuarios
    public EspecUsuario anyadirUsuBDD (EspecUsuario user) throws RemoteException;
    public EspecUsuario getUsuBDD (String userName) throws RemoteException;
    public void removeUsuBDD(EspecUsuario userName) throws RemoteException;

    // Gestión de mercancías
    public ConcurrentHashMap<MercanciaId, EspecMercancia> getMercancias () throws RemoteException;

    // Gestión de ofertas
    public EspecOferta anyadirOfertaBDD (EspecOferta especOferta) throws RemoteException;
    public void removeOfertaBDD (OfertaId ofertaId) throws RemoteException;
    public ConcurrentHashMap<OfertaId, EspecOferta> getOfertasBDD () throws RemoteException;
    public ConcurrentHashMap<OfertaId, EspecOferta> getOfertasBDD (UsuarioId usuarioId) throws RemoteException;
}
