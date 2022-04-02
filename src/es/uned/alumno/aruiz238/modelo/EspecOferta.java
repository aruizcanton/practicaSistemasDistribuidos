/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/

package es.uned.alumno.aruiz238.modelo;

import java.io.Serializable;

public class EspecOferta implements Serializable {
    private OfertaId ofertaId;
    private EspecMercancia especMercancia;
    private float precio;
    private float kilos;
    private UsuarioId usuarioId;    // Distribuidor que ha introducido la oferta

    public EspecOferta (OfertaId ofertaId, EspecMercancia especMercancia, float precio, float kilos, UsuarioId usuarioId) {
        this.ofertaId = ofertaId;
        this.especMercancia = especMercancia;
        this.precio = precio;
        this.kilos = kilos;
        this.usuarioId = usuarioId;
    }
    public  EspecOferta (EspecMercancia especMercancia, float precio, float kilos, UsuarioId usuarioId) {
        this.especMercancia = especMercancia;
        this.precio = precio;
        this.kilos = kilos;
        this.usuarioId = usuarioId;
    }
    public OfertaId getOfertaId() {
        return ofertaId;
    }

    public EspecMercancia getEspecMercancia() {
        return especMercancia;
    }

    public float getKilos() {
        return kilos;
    }

    public float getPrecio() {
        return precio;
    }

    public UsuarioId getUsuarioId() {
        return usuarioId;
    }

    public void setOfertaId(OfertaId ofertaId) {
        this.ofertaId = ofertaId;
    }

    public void setEspecMercancia(EspecMercancia especMercancia) {
        this.especMercancia = especMercancia;
    }

    public void setKilos(float kilos) {
        this.kilos = kilos;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

}
