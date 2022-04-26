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
    private DemandaListener demandaListener;

    public EspecDemanda (EspecMercancia especMercancia, UsuarioId usuarioId, DemandaListener demandaListener) {
        this.especMercancia = especMercancia;
        this.usuarioId = usuarioId;
        this.demandaListener = demandaListener;
    }
    public EspecDemanda (DemandaId demandaId, EspecMercancia especMercancia, UsuarioId usuarioId, DemandaListener demandaListener) {
        this.demandaId = demandaId;
        this.especMercancia = especMercancia;
        this.usuarioId = usuarioId;
        this.demandaListener = demandaListener;
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

    public DemandaListener getDemandaListener() {
        return demandaListener;
    }

    public void setDemandaListener(DemandaListener demandaListener) {
        this.demandaListener = demandaListener;
    }
}
