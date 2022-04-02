package es.uned.alumno.aruiz238.modelo;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioId implements Serializable {
    private final int userId;
    private final int hashCode;

    public UsuarioId(int userId) {
        this.userId = userId;
        this.hashCode = Objects.hashCode(userId);
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioId usuarioId = (UsuarioId) o;
        return userId == usuarioId.userId && hashCode == usuarioId.hashCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, hashCode);
    }
}
