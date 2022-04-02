/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.modelo;

import java.io.Serializable;
import java.util.Objects;

public class MercanciaId implements Serializable {
    private final int mercanciaId;
    private final int hashCode;

    public MercanciaId(int mercanciaId) {
        this.mercanciaId = mercanciaId;
        this.hashCode = Objects.hashCode(mercanciaId);
    }

    public int getTipoId() {
        return mercanciaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MercanciaId that = (MercanciaId) o;
        return mercanciaId == that.mercanciaId && hashCode == that.hashCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mercanciaId, hashCode);
    }
}
