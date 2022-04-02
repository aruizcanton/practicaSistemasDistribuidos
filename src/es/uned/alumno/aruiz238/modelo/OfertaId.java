package es.uned.alumno.aruiz238.modelo;

import java.io.Serializable;
import java.util.Objects;

public class OfertaId implements Serializable {
    private final int ofertaId;
    private final int hashCode;

    public OfertaId (int ofertaId) {

        this.ofertaId = ofertaId;
        this.hashCode = Objects.hashCode(ofertaId);
    }

    public int getOfertaId() {
        return ofertaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfertaId ofertaId1 = (OfertaId) o;
        return ofertaId == ofertaId1.ofertaId && hashCode == ofertaId1.hashCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ofertaId, hashCode);
    }
}
