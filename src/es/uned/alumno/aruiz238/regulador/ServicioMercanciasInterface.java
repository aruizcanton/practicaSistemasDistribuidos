/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.regulador;

import es.uned.alumno.aruiz238.modelo.*;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static es.uned.alumno.aruiz238.comm.IniciarRMI.registryHost;
import static es.uned.alumno.aruiz238.comm.IniciarRMI.registryPort;

public interface ServicioMercanciasInterface extends Remote {

    public static int idProveedorServDatos = 3;
    public static String NOMBRE_SERVICIO_MERCANCIAS = "rmi://" + registryHost + ":" + registryPort + "/serviciomercancias/" + idProveedorServDatos;

    //
    // METODOS PARA GESTION DE MERCANCIAS
    public ConcurrentHashMap<MercanciaId, EspecMercancia> getMercancias() throws RemoteException, NotBoundException;

    //
    //METODOS PARA GESTION DE OFERTAS
    //
    public EspecOferta anyadirOferta (EspecOferta especOferta) throws RemoteException, NotBoundException;
    public void removeOferta (OfertaId ofertaId) throws RemoteException, NotBoundException;
    public ConcurrentHashMap<OfertaId, EspecOferta> getOfertas () throws RemoteException, NotBoundException;
    public ConcurrentHashMap<OfertaId, EspecOferta> getOfertas (UsuarioId usuarioId) throws RemoteException, NotBoundException;
    public ConcurrentHashMap<OfertaId, EspecOferta> getOfertas (MercanciaId mercanciaId) throws  RemoteException, NotBoundException;
    public List<EspecOferta> getOfertasDemandadas (UsuarioId usuarioId) throws RemoteException, NotBoundException;
    public ConcurrentHashMap<DemandaId, EspecDemanda> getDemandas () throws RemoteException, NotBoundException;
    public ConcurrentHashMap<DemandaId, EspecDemanda> getDemandas ( UsuarioId usuarioId) throws RemoteException, NotBoundException;
    public EspecDemanda anyadirDemanda (EspecDemanda especDemanda) throws RemoteException, NotBoundException;
    public void removeDemanda (DemandaId demandaId) throws RemoteException, NotBoundException;
}
