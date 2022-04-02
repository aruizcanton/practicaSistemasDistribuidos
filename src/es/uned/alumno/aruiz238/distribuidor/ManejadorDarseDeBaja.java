package es.uned.alumno.aruiz238.distribuidor;

import es.uned.alumno.aruiz238.modelo.CatalogoOfertas;
import es.uned.alumno.aruiz238.modelo.EspecUsuario;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ManejadorDarseDeBaja {
    private final CatalogoOfertas ctlgoOfer;
    private final EspecUsuario especUsuario;
    public ManejadorDarseDeBaja (CatalogoOfertas ctlgoOfer, EspecUsuario especUsuario) {
        this.ctlgoOfer = ctlgoOfer;
        this.especUsuario = especUsuario;
    }
    public void darseDeBaja (EspecUsuario especUsuario) throws NotBoundException, RemoteException {
        ctlgoOfer.darDeBajaCatalogo();
        especUsuario.darseDeBaja();
    }
}
