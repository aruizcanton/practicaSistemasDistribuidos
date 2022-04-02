package es.uned.alumno.aruiz238.distribuidor;

import es.uned.alumno.aruiz238.modelo.EspecOferta;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicioVenta extends Remote {
    public boolean comprarMercancia (EspecOferta especOferta) throws RemoteException;
}
