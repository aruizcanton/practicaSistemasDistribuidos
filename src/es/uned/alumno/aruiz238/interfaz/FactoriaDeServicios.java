package es.uned.alumno.aruiz238.interfaz;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class FactoriaDeServicios {
    private static FactoriaDeServicios instancia;
    private IAdaptadorServMercancias adaptadorServMercancias;
    private IAdaptadorServAutenticacion adaptadorServAutenticacion;

    private FactoriaDeServicios() {

    }

    public static synchronized FactoriaDeServicios getInstancia() {
        if (instancia == null) {
            instancia = new FactoriaDeServicios();
        }
        return instancia;
    }

    public synchronized IAdaptadorServMercancias getAdaptadorServMercancias() throws RemoteException, NotBoundException {
        if (adaptadorServMercancias == null) {
            adaptadorServMercancias = new IAdaptadorServMercancias();
        }
        return adaptadorServMercancias;
    }
    public  synchronized IAdaptadorServAutenticacion getAdaptadorServAutenticacion() throws RemoteException, NotBoundException {
        if (adaptadorServAutenticacion == null) {
            adaptadorServAutenticacion = new IAdaptadorServAutenticacion();
        }
        return adaptadorServAutenticacion;
    }
}
