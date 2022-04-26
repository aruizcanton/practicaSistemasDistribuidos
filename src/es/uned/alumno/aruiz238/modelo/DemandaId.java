/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.modelo;

import java.io.Serializable;
import java.util.Objects;

public class DemandaId implements Serializable {
    private final int demadaId;
    private final int hashCode;

    public DemandaId(int demadaId) {
        this.demadaId = demadaId;
        this.hashCode = Objects.hashCode(demadaId);
    }

    public int getDemadaId() {
        return demadaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemandaId demandaId = (DemandaId) o;
        return demadaId == demandaId.demadaId && hashCode == demandaId.hashCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(demadaId, hashCode);
    }
}
