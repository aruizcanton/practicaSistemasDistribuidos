/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/

package es.uned.alumno.aruiz238.modelo;

import java.io.Serializable;

public class EspecDemanda implements Serializable {
    private DemandaId demandaId;
    private EspecMercancia especMercancia;
    private UsuarioId usuarioId;    // Cliente que ha introducido la demanda

    public EspecDemanda (EspecMercancia especMercancia, UsuarioId usuarioId) {
        this.especMercancia = especMercancia;
        this.usuarioId = usuarioId;
    }
    public EspecDemanda (DemandaId demandaId, EspecMercancia especMercancia, UsuarioId usuarioId) {
        this.demandaId = demandaId;
        this.especMercancia = especMercancia;
        this.usuarioId = usuarioId;
    }

    public void setEspecMercancia(EspecMercancia especMercancia) {
        this.especMercancia = especMercancia;
    }

    public EspecMercancia getEspecMercancia() {
        return especMercancia;
    }

    public void setDemandaId(DemandaId demandaId) {
        this.demandaId = demandaId;
    }

    public DemandaId getDemandaId() {
        return demandaId;
    }

    public void setUsuarioId(UsuarioId usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UsuarioId getUsuarioId() {
        return usuarioId;
    }
}
