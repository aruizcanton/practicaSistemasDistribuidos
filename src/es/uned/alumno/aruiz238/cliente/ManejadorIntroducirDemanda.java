package es.uned.alumno.aruiz238.cliente;

import es.uned.alumno.aruiz238.modelo.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class ManejadorIntroducirDemanda {
    private final CatalogoMercancias ctlgoMer;
    private final CatalogoDemandas ctlgoDeman;
    private final UsuarioId usuarioId;

    public ManejadorIntroducirDemanda (CatalogoMercancias catalogoMercancias, CatalogoDemandas catalogoDemandas, UsuarioId usuarioId) {
        this.ctlgoMer = catalogoMercancias;
        this.ctlgoDeman = catalogoDemandas;
        this.usuarioId = usuarioId;
    }
    public List<EspecMercancia> introducirDemanda() {
        return ctlgoMer.getEspecsMer();
    }
    public EspecDemanda altaDemanda (MercanciaId mercanciaId) throws NotBoundException, RemoteException {
        final EspecMercancia especMercancia = ctlgoMer.getEspecMercancia(mercanciaId);
        final EspecDemanda especDemandaTmp = new EspecDemanda(especMercancia, usuarioId);
        return ctlgoDeman.agregaNewDeman(especDemandaTmp);
    }
}
