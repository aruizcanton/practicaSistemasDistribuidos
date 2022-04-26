/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.cliente;

import es.uned.alumno.aruiz238.modelo.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class ManejadorIntroducirDemanda {
    private final CatalogoMercancias ctlgoMer;
    private final CatalogoDemandas ctlgoDeman;

    public ManejadorIntroducirDemanda (CatalogoMercancias catalogoMercancias, CatalogoDemandas catalogoDemandas) {
        this.ctlgoMer = catalogoMercancias;
        this.ctlgoDeman = catalogoDemandas;
    }

    public List<EspecMercancia> introducirDemanda () {

        return ctlgoMer.getEspecsMer();
    }

    public EspecDemanda altaDemanda (MercanciaId mercanciaId, UsuarioId usuarioId, DemandaListener demandaListener) throws NotBoundException, RemoteException {
        final EspecMercancia especMercancia = ctlgoMer.getEspecMercancia(mercanciaId);
        final EspecDemanda especDemandaTmp = new EspecDemanda(especMercancia, usuarioId, demandaListener);
        return ctlgoDeman.agregaNewDeman(especDemandaTmp);
    }
}
