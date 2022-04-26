/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.distribuidor;

import es.uned.alumno.aruiz238.modelo.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class ManejadorIntroducirOferta {
    private final CatalogoMercancias ctlgoMer;
    private final CatalogoOfertas ctlgoOfer;

    ManejadorIntroducirOferta (CatalogoMercancias ctlgoMer, CatalogoOfertas ctlgoOfer) {
        this.ctlgoMer = ctlgoMer;
        this.ctlgoOfer = ctlgoOfer;
    }

    public List<EspecMercancia> introducirOferta () {

        return ctlgoMer.getEspecsMer();
    }

    public EspecOferta altaOferta (MercanciaId mercanciaId, float precio, float kilos, UsuarioId usuarioId) throws NotBoundException, RemoteException {
        final EspecMercancia especMercancia = ctlgoMer.getEspecMercancia(mercanciaId);
        final EspecOferta especOfertaTmp = new EspecOferta (especMercancia, precio, kilos, usuarioId);
        return ctlgoOfer.agregaNewOfer (especOfertaTmp);
    }
}
