package es.uned.alumno.aruiz238.distribuidor;

import es.uned.alumno.aruiz238.modelo.CatalogoOfertas;
import es.uned.alumno.aruiz238.modelo.EspecUsuario;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ManejadorDarseDeBaja {
    private final CatalogoOfertas ctlgoOfer;

    public ManejadorDarseDeBaja (CatalogoOfertas ctlgoOfer) {
        this.ctlgoOfer = ctlgoOfer;
    }
    public void darseDeBaja (EspecUsuario especUsuario) throws NotBoundException, RemoteException {
        ctlgoOfer.darDeBajaCatalogo();
        especUsuario.darseDeBaja();
    }
}
