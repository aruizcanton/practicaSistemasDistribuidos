/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/

package es.uned.alumno.aruiz238.modelo;

import java.io.Serializable;

public class EspecMercancia implements Serializable {
    private MercanciaId mercanciaId;
    private  String descripcion;

    public EspecMercancia(MercanciaId mercanciaId, String descripcion) {
        this.mercanciaId = mercanciaId;
        this.descripcion = descripcion;
    }
    public MercanciaId getMercanciaId() {
        return mercanciaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setMercanciaId(MercanciaId tipo) {
        this.mercanciaId = mercanciaId;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
