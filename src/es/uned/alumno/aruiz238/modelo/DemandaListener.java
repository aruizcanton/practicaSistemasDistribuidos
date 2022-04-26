/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.modelo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DemandaListener extends Remote {
    void nuevaOfertaRecibida (EspecOferta especOferta) throws RemoteException;
}
