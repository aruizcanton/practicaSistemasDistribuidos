/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.regulador;

import es.uned.alumno.aruiz238.modelo.CatalogoDemandas;
import es.uned.alumno.aruiz238.modelo.EspecDemanda;

import java.util.List;

public class ManejadorListarDemandas {
    private CatalogoDemandas ctlgoDeman;
    ManejadorListarDemandas(CatalogoDemandas catalogoDemandas) {
        this.ctlgoDeman = catalogoDemandas;
    }
    List<EspecDemanda> getDemandasActuales() {
        return ctlgoDeman.getDemandas();
    }
}
