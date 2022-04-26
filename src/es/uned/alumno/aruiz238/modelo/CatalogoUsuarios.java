/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.modelo;

import es.uned.alumno.aruiz238.interfaz.FactoriaDeServicios;
import es.uned.alumno.aruiz238.interfaz.IAdaptadorServAutenticacion;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CatalogoUsuarios {
    private final ConcurrentHashMap<String, EspecUsuario> especsUsu;

    public CatalogoUsuarios() throws NotBoundException, RemoteException {
        final IAdaptadorServAutenticacion servAutenticacion = FactoriaDeServicios.getInstancia().getAdaptadorServAutenticacion();
        especsUsu = servAutenticacion.getUsuarios();
    }
    private final Comparator<EspecUsuario> especUsuarioComparator = new Comparator<>() {
        @Override
        public int compare(EspecUsuario o1, EspecUsuario o2) {
            return o1.getUserId().getUserId() - o2.getUserId().getUserId();
        }
    };

    public List<EspecUsuario> getUsuarios() {
        final List<EspecUsuario> especUsuarioList = new ArrayList<>(especsUsu.values());
        especUsuarioList.sort(especUsuarioComparator);
        return especUsuarioList;
    }
    public List<EspecUsuario> getUsuarios(TipoUsuEnum tipoUsuario) {
        final List<EspecUsuario> especUsuarioList = new ArrayList<>();
        for (EspecUsuario value: especsUsu.values()
             ) {
                if (value.getTipoUsuario() == tipoUsuario) {
                    especUsuarioList.add(value);
                }
        }
        especUsuarioList.sort(especUsuarioComparator);
        return especUsuarioList;
    }
}
