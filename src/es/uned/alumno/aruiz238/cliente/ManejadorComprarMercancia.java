/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.cliente;

import es.uned.alumno.aruiz238.interfaz.FactoriaDeServicios;
import es.uned.alumno.aruiz238.interfaz.IAdaptadorSerCompra;
import es.uned.alumno.aruiz238.modelo.EspecOferta;
import es.uned.alumno.aruiz238.modelo.OfertaId;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class ManejadorComprarMercancia {
    private final List<EspecOferta> ofertasDemandadas;
    ManejadorComprarMercancia (List<EspecOferta> ofertasDemandadas) {
        this.ofertasDemandadas = ofertasDemandadas;
    }
    public boolean comprarMercancia (OfertaId ofertaId) throws NotBoundException, RemoteException {
        for (EspecOferta value: ofertasDemandadas
             ) {
                    if (value.getOfertaId().equals(ofertaId)) {
                        // Se trata de la oferta elegida para ser comprada
                        // LLevo a cabo la compra
                        final IAdaptadorSerCompra serCompra = FactoriaDeServicios.getInstancia().getAdaptadorSerCompra();
                        return serCompra.realizarCompra(value);
                    }
        }
        return false;
    }
}
