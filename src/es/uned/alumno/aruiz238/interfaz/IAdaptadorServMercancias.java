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
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static es.uned.alumno.aruiz238.comm.IniciarRMI.registryPort;

public class IAdaptadorServMercancias {

    public IAdaptadorServMercancias () {

    }

    // Mercancías
    public ConcurrentHashMap<MercanciaId, EspecMercancia> getMercancias() throws RemoteException, NotBoundException {
        final  Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioMercanciasInterface servicioMercanciasInterface = (ServicioMercanciasInterface) registry.lookup(ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS);
        return servicioMercanciasInterface.getMercancias();
    }

    // Ofertas
    public EspecOferta anyadirOferta (EspecOferta especOferta) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioMercanciasInterface servicioMercanciasInterface = (ServicioMercanciasInterface) registry.lookup(ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS);
        return servicioMercanciasInterface.anyadirOferta(especOferta);
    }

    public void removeOferta (OfertaId ofertaId) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioMercanciasInterface servicioMercanciasInterface = (ServicioMercanciasInterface) registry.lookup(ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS);
        servicioMercanciasInterface.removeOferta(ofertaId);
    }

    public ConcurrentHashMap<OfertaId, EspecOferta> getOfertas () throws RemoteException, NotBoundException {
        final  Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioMercanciasInterface servicioMercanciasInterface = (ServicioMercanciasInterface) registry.lookup(ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS);
        return servicioMercanciasInterface.getOfertas();
    }

    public ConcurrentHashMap<OfertaId, EspecOferta> getOfertas (UsuarioId usuarioId) throws RemoteException, NotBoundException {
        final  Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioMercanciasInterface servicioMercanciasInterface = (ServicioMercanciasInterface) registry.lookup(ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS);
        return servicioMercanciasInterface.getOfertas(usuarioId);
    }

    public ConcurrentHashMap<OfertaId, EspecOferta> getOfertas (MercanciaId mercanciaId) throws RemoteException, NotBoundException {
        final  Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioMercanciasInterface servicioMercanciasInterface = (ServicioMercanciasInterface) registry.lookup(ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS);
        return servicioMercanciasInterface.getOfertas(mercanciaId);
    }

    public EspecDemanda anyadirDemanda (EspecDemanda especDemanda) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioMercanciasInterface servicioMercanciasInterface = (ServicioMercanciasInterface) registry.lookup(ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS);
        return servicioMercanciasInterface.anyadirDemanda(especDemanda);
    }

    public void removeDemanda (DemandaId demandaId) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioMercanciasInterface servicioMercanciasInterface = (ServicioMercanciasInterface) registry.lookup(ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS);
        servicioMercanciasInterface.removeDemanda(demandaId);
    }

    public ConcurrentHashMap<DemandaId, EspecDemanda> getDemandas () throws RemoteException, NotBoundException {
        final  Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioMercanciasInterface servicioMercanciasInterface = (ServicioMercanciasInterface) registry.lookup(ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS);
        return servicioMercanciasInterface.getDemandas();
    }

    public ConcurrentHashMap<DemandaId, EspecDemanda> getDemandas (UsuarioId usuarioId) throws RemoteException, NotBoundException {
        final  Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioMercanciasInterface servicioMercanciasInterface = (ServicioMercanciasInterface) registry.lookup(ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS);
        return servicioMercanciasInterface.getDemandas(usuarioId);
    }
    public List<EspecOferta> getOfertasDemandadas (UsuarioId usuarioId) throws RemoteException, NotBoundException {
        final  Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioMercanciasInterface servicioMercanciasInterface = (ServicioMercanciasInterface) registry.lookup(ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS);
        return servicioMercanciasInterface.getOfertasDemandadas(usuarioId);
    }

}
