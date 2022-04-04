package es.uned.alumno.aruiz238.modelo;

import es.uned.alumno.aruiz238.interfaz.FactoriaDeServicios;
import es.uned.alumno.aruiz238.interfaz.IAdaptadorServMercancias;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CatalogoDemandas {
    private final ConcurrentHashMap<DemandaId, EspecDemanda> especsDeman;
    private final IAdaptadorServMercancias servMercancias;

    public CatalogoDemandas () throws NotBoundException, RemoteException {
        servMercancias = FactoriaDeServicios.getInstancia().getAdaptadorServMercancias();
        especsDeman = servMercancias.getDemandas();
    }
    public CatalogoDemandas (UsuarioId usuarioId) throws NotBoundException, RemoteException {
        servMercancias = FactoriaDeServicios.getInstancia().getAdaptadorServMercancias();
        especsDeman = servMercancias.getDemandas(usuarioId);
    }
    private final Comparator<EspecDemanda> especDemandaComparator = Comparator.comparingInt(o -> o.getDemandaId().getDemadaId());
    public EspecDemanda agregaNewDeman (EspecDemanda especDemanda) throws NotBoundException, RemoteException {
        final EspecDemanda especDemanda1 = servMercancias.anyadirDemanda (especDemanda);
        especsDeman.put(especDemanda1.getDemandaId(), especDemanda1);
        return especDemanda1;
    }
    public List<EspecDemanda> getDemandas () {
        final List<EspecDemanda> especDemandas = new ArrayList<>(especsDeman.values());
        especDemandas.sort(especDemandaComparator);
        return especDemandas;
    }
    public void removeDeman (DemandaId demandaId) throws NotBoundException, RemoteException {
        servMercancias.removeDemanda(demandaId);
        especsDeman.remove(demandaId);
    }
    public void darDeBajaCatalogo () throws NotBoundException, RemoteException {
        for (EspecDemanda value:
             especsDeman.values()) {
                servMercancias.removeDemanda(value.getDemandaId());
        }
        especsDeman.clear();
    }
}
