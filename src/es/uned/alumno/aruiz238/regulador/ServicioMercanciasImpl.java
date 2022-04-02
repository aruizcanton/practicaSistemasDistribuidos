/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/

package es.uned.alumno.aruiz238.regulador;

import es.uned.alumno.aruiz238.comm.IniciarRMI;
import es.uned.alumno.aruiz238.modelo.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ConcurrentHashMap;

public class ServicioMercanciasImpl implements ServicioMercanciasInterface {
    // Mercancías
    @Override
    public ConcurrentHashMap<MercanciaId, EspecMercancia> getMercancias() throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(IniciarRMI.registryPort);
        final ServicioDatosInterface servDatos = (ServicioDatosInterface) registry.lookup(ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
        final ConcurrentHashMap<MercanciaId, EspecMercancia> mercancias = servDatos.getMercancias();
        return mercancias;
    }

    // Ofertas
    @Override
    public EspecOferta anyadirOferta(EspecOferta especOferta) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(IniciarRMI.registryPort);
        final ServicioDatosInterface servDatos = (ServicioDatosInterface) registry.lookup(ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
        final EspecOferta especOferta1 = servDatos.anyadirOfertaBDD(especOferta);
        return especOferta1;
    }
    public void removeOferta (OfertaId ofertaId) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(IniciarRMI.registryPort);
        final ServicioDatosInterface servDatos = (ServicioDatosInterface) registry.lookup(ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
        servDatos.removeOfertaBDD(ofertaId);
    }
    public ConcurrentHashMap<OfertaId, EspecOferta> getOfertas() throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(IniciarRMI.registryPort);
        final ServicioDatosInterface servDatos = (ServicioDatosInterface) registry.lookup(ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
        final ConcurrentHashMap<OfertaId, EspecOferta> ofertas = servDatos.getOfertasBDD();
        return ofertas;
    }

    @Override
    public ConcurrentHashMap<OfertaId, EspecOferta> getOfertas(UsuarioId usuarioId) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(IniciarRMI.registryPort);
        final ServicioDatosInterface servDatos = (ServicioDatosInterface) registry.lookup(ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
        final ConcurrentHashMap<OfertaId, EspecOferta> ofertas = servDatos.getOfertasBDD(usuarioId);
        return ofertas;
    }
}
