/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.interfaz;

import es.uned.alumno.aruiz238.modelo.*;
import es.uned.alumno.aruiz238.regulador.ServicioMercanciasInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ConcurrentHashMap;

import static es.uned.alumno.aruiz238.comm.IniciarRMI.registryPort;

public class IAdaptadorServMercancias {

    public IAdaptadorServMercancias () {

    }

    // Mercancías
    public ConcurrentHashMap<MercanciaId, EspecMercancia> getMercancias() throws RemoteException, NotBoundException {
        final  Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioMercanciasInterface servicioMercanciasInterface = (ServicioMercanciasInterface) registry.lookup(ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS);
        ConcurrentHashMap<MercanciaId, EspecMercancia> mercancias = servicioMercanciasInterface.getMercancias();
        return mercancias;
    }

    // Ofertas
    public EspecOferta anyadirOferta (EspecOferta especOferta) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioMercanciasInterface servicioMercanciasInterface = (ServicioMercanciasInterface) registry.lookup(ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS);
        final  EspecOferta especOferta1 = servicioMercanciasInterface.anyadirOferta(especOferta);
        return especOferta1;
    }
    public void removeOferta (OfertaId ofertaId) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioMercanciasInterface servicioMercanciasInterface = (ServicioMercanciasInterface) registry.lookup(ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS);
        servicioMercanciasInterface.removeOferta(ofertaId);
    }
    public ConcurrentHashMap<OfertaId, EspecOferta> getOfertas() throws RemoteException, NotBoundException {
        final  Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioMercanciasInterface servicioMercanciasInterface = (ServicioMercanciasInterface) registry.lookup(ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS);
        ConcurrentHashMap<OfertaId, EspecOferta> ofertas = servicioMercanciasInterface.getOfertas();
        return ofertas;
    }
    public ConcurrentHashMap<OfertaId, EspecOferta> getOfertas(UsuarioId usuarioId) throws RemoteException, NotBoundException {
        final  Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioMercanciasInterface servicioMercanciasInterface = (ServicioMercanciasInterface) registry.lookup(ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS);
        ConcurrentHashMap<OfertaId, EspecOferta> ofertas = servicioMercanciasInterface.getOfertas(usuarioId);
        return ofertas;
    }

}
