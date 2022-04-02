/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.distribuidor;

import es.uned.alumno.aruiz238.interfaz.FactoriaInterfazGrafica;
import es.uned.alumno.aruiz238.interfaz.IAdaptadorInterfazGrafica;
import es.uned.alumno.aruiz238.modelo.CatalogoOfertas;
import es.uned.alumno.aruiz238.modelo.OfertaId;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ManejadorQuitarOferta {
    private CatalogoOfertas ctlgoOfer;

    public ManejadorQuitarOferta (CatalogoOfertas ctlgoOfer) {
        this.ctlgoOfer = ctlgoOfer;
    }
    public void quitarOferta (OfertaId ofertaId) throws NotBoundException, RemoteException {
        final IAdaptadorInterfazGrafica adaptadorInterfazGrafica = FactoriaInterfazGrafica.getInstancia().getAdaptadorInterfazGrafica();
        ctlgoOfer.removeOfer(ofertaId);
    }
}
