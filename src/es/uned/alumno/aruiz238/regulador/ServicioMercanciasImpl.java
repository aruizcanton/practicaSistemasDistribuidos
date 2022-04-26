/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/

package es.uned.alumno.aruiz238.regulador;

import es.uned.alumno.aruiz238.comm.IniciarRMI;
import es.uned.alumno.aruiz238.modelo.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ServicioMercanciasImpl implements ServicioMercanciasInterface {
    // Mercancías
    @Override
    public ConcurrentHashMap<MercanciaId, EspecMercancia> getMercancias() throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(IniciarRMI.registryPort);
        final ServicioDatosInterface servDatos = (ServicioDatosInterface) registry.lookup(ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
        return servDatos.getMercancias();
    }

    // Ofertas
    @Override
    public EspecOferta anyadirOferta (EspecOferta especOferta) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(IniciarRMI.registryPort);
        final ServicioDatosInterface servDatos = (ServicioDatosInterface) registry.lookup (ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
        final EspecOferta especOferta1 = servDatos.anyadirOfertaBDD(especOferta);
        final List<EspecDemanda> especDemandas = new ArrayList<>(servDatos.getDemandasBDD().values());
        for (EspecDemanda value: especDemandas) {
            if (value.getEspecMercancia().getMercanciaId().equals(especOferta.getEspecMercancia().getMercanciaId())) {
                value.getDemandaListener().nuevaOfertaRecibida(especOferta1);
            }
        }
        return especOferta1;
    }
    public void removeOferta (OfertaId ofertaId) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(IniciarRMI.registryPort);
        final ServicioDatosInterface servDatos = (ServicioDatosInterface) registry.lookup(ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
        servDatos.removeOfertaBDD(ofertaId);
    }
    public ConcurrentHashMap<OfertaId, EspecOferta> getOfertas() throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(IniciarRMI.registryPort);
        final ServicioDatosInterface servDatos = (ServicioDatosInterface) registry.lookup(ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
        return servDatos.getOfertasBDD();
    }

    @Override
    public ConcurrentHashMap<OfertaId, EspecOferta> getOfertas(UsuarioId usuarioId) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(IniciarRMI.registryPort);
        final ServicioDatosInterface servDatos = (ServicioDatosInterface) registry.lookup(ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
        final ConcurrentHashMap<OfertaId, EspecOferta> ofertasTmp = servDatos.getOfertasBDD();
        final ConcurrentHashMap<OfertaId, EspecOferta> ofertasTmp1 = new ConcurrentHashMap<>();
        for (EspecOferta value: ofertasTmp.values()
             ) {
                    if (value.getUsuarioId().equals(usuarioId)) {
                        ofertasTmp1.put(value.getOfertaId(), value);
                    }
        }
        return ofertasTmp1;
    }

    @Override
    public List<EspecOferta> getOfertasDemandadas(UsuarioId usuarioId) throws RemoteException, NotBoundException {
        final List<EspecDemanda> especDemandas = new ArrayList<>(this.getDemandas(usuarioId).values());
        final List<EspecOferta> especOfertas = new ArrayList<>(this.getOfertas().values());
        final List<EspecOferta> especsOfertaTmp = new ArrayList<>();
        for (EspecDemanda valueDemanda: especDemandas) {
            for (EspecOferta valueOferta: especOfertas) {
                if (valueOferta.getEspecMercancia().getMercanciaId().equals(valueDemanda.getEspecMercancia().getMercanciaId())) {
                    especsOfertaTmp.add(valueOferta);
                }
            }
        }
        return especsOfertaTmp;
    }

    @Override
    public ConcurrentHashMap<OfertaId, EspecOferta> getOfertas(MercanciaId mercanciaId) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(IniciarRMI.registryPort);
        final ServicioDatosInterface servDatos = (ServicioDatosInterface) registry.lookup(ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
        final ConcurrentHashMap<OfertaId, EspecOferta> ofertasTemp = servDatos.getOfertasBDD();
        final ConcurrentHashMap<OfertaId, EspecOferta> ofertasTemp1 = new ConcurrentHashMap<>();
        for (EspecOferta value: ofertasTemp.values()
        ) {
            if (value.getEspecMercancia().getMercanciaId().equals(mercanciaId)) {
                ofertasTemp1.put(value.getOfertaId(), value);
            }
        }
        return ofertasTemp1;
    }

    @Override
    public EspecDemanda anyadirDemanda(EspecDemanda especDemanda) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(IniciarRMI.registryPort);
        final ServicioDatosInterface servDatos = (ServicioDatosInterface) registry.lookup(ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
        return servDatos.anyadirDemandaBDD(especDemanda);
    }

    @Override
    public void removeDemanda(DemandaId demandaId) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(IniciarRMI.registryPort);
        final ServicioDatosInterface servDatos = (ServicioDatosInterface) registry.lookup(ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
        servDatos.removeDemandaBDD(demandaId);
    }

    @Override
    public ConcurrentHashMap<DemandaId, EspecDemanda> getDemandas() throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(IniciarRMI.registryPort);
        final ServicioDatosInterface servDatos = (ServicioDatosInterface) registry.lookup(ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
        return servDatos.getDemandasBDD();
    }

    @Override
    public ConcurrentHashMap<DemandaId, EspecDemanda> getDemandas(UsuarioId usuarioId) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(IniciarRMI.registryPort);
        final ServicioDatosInterface servDatos = (ServicioDatosInterface) registry.lookup(ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
        return servDatos.getDemandasBDD(usuarioId);
    }
}
