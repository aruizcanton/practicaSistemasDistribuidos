/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.distribuidor;

import es.uned.alumno.aruiz238.interfaz.FactoriaInterfazGrafica;
import es.uned.alumno.aruiz238.interfaz.IAdaptadorInterfazGrafica;
import es.uned.alumno.aruiz238.modelo.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class ManejadorIntroducirOferta {
    private CatalogoMercancias ctlgoMer;
    private CatalogoOfertas ctlgoOfer;
    private UsuarioId usuarioId;

    ManejadorIntroducirOferta (CatalogoMercancias ctlgoMer, CatalogoOfertas ctlgoOfer, UsuarioId usuarioId) {
        this.ctlgoMer = ctlgoMer;
        this.ctlgoOfer = ctlgoOfer;
        this.usuarioId = usuarioId;
    }

    public List<EspecMercancia> introducirOferta () {

        return ctlgoMer.getEspecsMer();
    }

    public EspecOferta altaOferta (MercanciaId mercanciaId, float precio, float kilos) throws NotBoundException, RemoteException {
        final IAdaptadorInterfazGrafica adaptadorInterfazGrafica = FactoriaInterfazGrafica.getInstancia().getAdaptadorInterfazGrafica();
        EspecOferta especOferta = null;
        final EspecMercancia especMercancia = ctlgoMer.getEspecMercancia(mercanciaId);
        final EspecOferta especOfertaTmp = new EspecOferta (especMercancia, precio, kilos, usuarioId);
        especOferta = ctlgoOfer.agregaNewOfer (especOfertaTmp);
        return especOferta;
    }
}
