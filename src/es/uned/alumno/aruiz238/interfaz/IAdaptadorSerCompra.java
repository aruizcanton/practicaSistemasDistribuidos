/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.interfaz;

import es.uned.alumno.aruiz238.distribuidor.ServicioCompraInterface;
import es.uned.alumno.aruiz238.modelo.EspecOferta;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static es.uned.alumno.aruiz238.comm.IniciarRMI.registryPort;

public class IAdaptadorSerCompra {
    IAdaptadorSerCompra () {}
    public boolean realizarCompra (EspecOferta especOferta) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(registryPort);
        final ServicioCompraInterface servicioCompraInterface = (ServicioCompraInterface) registry.lookup(ServicioCompraInterface.NOMBRE_SERVICIO_COMPRA + especOferta.getUsuarioId().getUserId());
        return servicioCompraInterface.comprarOferta(especOferta);
    }
}
