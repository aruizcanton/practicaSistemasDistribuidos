/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.regulador;

import es.uned.alumno.aruiz238.modelo.CatalogoOfertas;
import es.uned.alumno.aruiz238.modelo.EspecOferta;

import java.util.List;

public class ManejadorListarOfertasActuales {
    private final CatalogoOfertas ctlgoOfer;
    ManejadorListarOfertasActuales (CatalogoOfertas catalogoOfertas) {
        this.ctlgoOfer = catalogoOfertas;
    }
    public List<EspecOferta> getOfertasActuales() {
        return ctlgoOfer.getOfertas();
    }
}
