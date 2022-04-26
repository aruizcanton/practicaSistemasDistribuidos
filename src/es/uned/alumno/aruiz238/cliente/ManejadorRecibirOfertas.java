/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.cliente;

import es.uned.alumno.aruiz238.interfaz.FactoriaDeServicios;
import es.uned.alumno.aruiz238.interfaz.IAdaptadorServMercancias;
import es.uned.alumno.aruiz238.modelo.CatalogoDemandas;
import es.uned.alumno.aruiz238.modelo.CatalogoOfertas;
import es.uned.alumno.aruiz238.modelo.EspecOferta;
import es.uned.alumno.aruiz238.modelo.UsuarioId;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class ManejadorRecibirOfertas {
    private final CatalogoDemandas catalogoDemandas;
    private final  CatalogoOfertas catalogoOfertas;
    private final UsuarioId usuarioId;
    public ManejadorRecibirOfertas (CatalogoDemandas catalogoDemandas, CatalogoOfertas catalogoOfertas, UsuarioId usuarioId) {
        this.catalogoDemandas = catalogoDemandas;
        this.catalogoOfertas = catalogoOfertas;
        this.usuarioId = usuarioId;
    }
    public List<EspecOferta> recibirOfertas () throws NotBoundException, RemoteException {
        //final List<EspecOferta> especsOfertaTmp = new ArrayList<>();
        //for (EspecDemanda valueDemanda: catalogoDemandas.getDemandas()) {
        //    for (EspecOferta valueOferta: catalogoOfertas.getOfertas()) {
        //        if (valueOferta.getEspecMercancia().getMercanciaId().equals(valueDemanda.getEspecMercancia().getMercanciaId())) {
        //            especsOfertaTmp.add(valueOferta);
        //        }
        //    }
        //}
        //return especsOfertaTmp;

        final IAdaptadorServMercancias servMercancias = FactoriaDeServicios.getInstancia().getAdaptadorServMercancias();
        return servMercancias.getOfertasDemandadas(usuarioId);
    }
}
