/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.distribuidor;

import es.uned.alumno.aruiz238.modelo.EspecOferta;

import java.rmi.Remote;
import java.rmi.RemoteException;
import static es.uned.alumno.aruiz238.comm.IniciarRMI.registryHost;
import static es.uned.alumno.aruiz238.comm.IniciarRMI.registryPort;


public interface ServicioCompraInterface extends Remote {
    public static String NOMBRE_SERVICIO_COMPRA = "rmi://" + registryHost + ":" + registryPort + "/serviciocompra/";

    public boolean comprarOferta(EspecOferta especOferta) throws RemoteException;
}
