/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.modelo;

import es.uned.alumno.aruiz238.interfaz.FactoriaDeServicios;
import es.uned.alumno.aruiz238.interfaz.IAdaptadorServMercancias;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CatalogoOfertas {
    private final ConcurrentHashMap<OfertaId, EspecOferta> especsOfer;
    private final IAdaptadorServMercancias servMercancias;

    public CatalogoOfertas() throws NotBoundException, RemoteException {
        servMercancias = FactoriaDeServicios.getInstancia().getAdaptadorServMercancias();
        especsOfer = servMercancias.getOfertas();
    }
    public CatalogoOfertas(UsuarioId usuarioId) throws NotBoundException, RemoteException {
        servMercancias = FactoriaDeServicios.getInstancia().getAdaptadorServMercancias();
        especsOfer = servMercancias.getOfertas(usuarioId);
    }
    private final Comparator<EspecOferta> especOfertaComparator = new Comparator<>() {
        @Override
        public int compare(EspecOferta o1, EspecOferta o2) {
            return o1.getOfertaId().getOfertaId() - o2.getOfertaId().getOfertaId();
        }
    };
    public EspecOferta agregaNewOfer(EspecOferta especOferta) throws NotBoundException, RemoteException {
        final EspecOferta especOferta1 = servMercancias.anyadirOferta(especOferta);
        especsOfer.put(especOferta1.getOfertaId(), especOferta1);
        return especOferta1;
    }
    public List<EspecOferta> getOfertas() {

        final List<EspecOferta> especOfertas = new ArrayList<>(especsOfer.values());
        especOfertas.sort(especOfertaComparator);
        return especOfertas;
    }
    public void removeOfer(OfertaId ofertaId) throws NotBoundException, RemoteException {
        servMercancias.removeOferta(ofertaId);
        especsOfer.remove(ofertaId);
    }

    public void darDeBajaCatalogo() throws NotBoundException, RemoteException {
        for (EspecOferta value:
                especsOfer.values()
             ) {
                    servMercancias.removeOferta(value.getOfertaId());
        }
        especsOfer.clear();
    }
}
