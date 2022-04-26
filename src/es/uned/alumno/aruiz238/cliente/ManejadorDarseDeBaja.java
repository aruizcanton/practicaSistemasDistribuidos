/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.cliente;

import es.uned.alumno.aruiz238.modelo.CatalogoDemandas;
import es.uned.alumno.aruiz238.modelo.EspecUsuario;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ManejadorDarseDeBaja {
    private final CatalogoDemandas ctlgoDeman;

    public ManejadorDarseDeBaja (CatalogoDemandas ctlgoDeman) {
        this.ctlgoDeman = ctlgoDeman;
    }
    public void darseDeBaja(EspecUsuario especUsuario) throws NotBoundException, RemoteException {
        ctlgoDeman.darDeBajaCatalogo();
        especUsuario.darseDeBaja();
    }

}
