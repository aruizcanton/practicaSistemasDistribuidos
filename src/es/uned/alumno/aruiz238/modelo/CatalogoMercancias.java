package es.uned.alumno.aruiz238.modelo;

import es.uned.alumno.aruiz238.interfaz.FactoriaDeServicios;
import es.uned.alumno.aruiz238.interfaz.IAdaptadorServMercancias;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CatalogoMercancias {
    private ConcurrentHashMap<MercanciaId, EspecMercancia> especsMer;
    private IAdaptadorServMercancias servMercancias;

    public CatalogoMercancias() {
            try {
                servMercancias = FactoriaDeServicios.getInstancia().getAdaptadorServMercancias();
                especsMer = servMercancias.getMercancias();
            } catch (RemoteException e) {
                System.out.println("Ha habido una excepción de comunicación. Excepción: RemoteException. Detalles:");
                e.printStackTrace();
            } catch (NotBoundException e) {
                System.out.println("Ha habido una excepción al conectar con el servicio remoto de mercancías. Excepción: NotBoundException. Detalles:");
                e.printStackTrace();
            }
    }
    private Comparator<EspecMercancia> especMercanciaComparator = new Comparator<EspecMercancia>() {
        @Override
        public int compare(EspecMercancia o1, EspecMercancia o2) {
            return o1.getMercanciaId().getTipoId() - o2.getMercanciaId().getTipoId();
        }
    };
    public List<EspecMercancia> getEspecsMer() {
        final List<EspecMercancia> especMercancias = new ArrayList<EspecMercancia>();

        for (EspecMercancia value:
                especsMer.values()) {
                    especMercancias.add(value);
        }
        Collections.sort(especMercancias, especMercanciaComparator);

        return especMercancias;
    }
    public EspecMercancia getEspecMercancia (MercanciaId mercanciaId) {
        return especsMer.get(mercanciaId);
    }
}
