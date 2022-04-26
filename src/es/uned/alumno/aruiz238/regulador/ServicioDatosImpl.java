/*
////////////////////////////////////////
 Autor: Ángel Ruiz Cantón
 E-mail: aruiz238@alumno.uned.es
////////////////////////////////////////
*/

package es.uned.alumno.aruiz238.regulador;

import es.uned.alumno.aruiz238.modelo.*;

import java.rmi.RemoteException;
import java.util.concurrent.ConcurrentHashMap;

public class ServicioDatosImpl implements ServicioDatosInterface {
     private static final ConcurrentHashMap<String, EspecUsuario> usuarios = new ConcurrentHashMap<>();
     private static final ConcurrentHashMap <OfertaId, EspecOferta> ofertas = new ConcurrentHashMap<>();
     private static final ConcurrentHashMap<MercanciaId, EspecMercancia> mercancias = new ConcurrentHashMap<>() {{
        put(new MercanciaId(1), new EspecMercancia(new MercanciaId(1), "Tomates"));
        put(new MercanciaId(2), new EspecMercancia(new MercanciaId(2), "Limones"));
        put(new MercanciaId(3), new EspecMercancia(new MercanciaId(3), "Naranjas"));
        put(new MercanciaId(4), new EspecMercancia(new MercanciaId(4), "Fresas"));
        put(new MercanciaId(5), new EspecMercancia(new MercanciaId(5), "Plátanos"));
        put(new MercanciaId(6), new EspecMercancia(new MercanciaId(6), "Melones"));
        put(new MercanciaId(7), new EspecMercancia(new MercanciaId(7), "Sandías"));
     }};
     private static final ConcurrentHashMap<DemandaId, EspecDemanda> demandas = new ConcurrentHashMap<>();
     private static int userId=0;
     private static int ofertaId =0;
     private static int demandaId=0;

     //
     // MÉTODOS PARA GESTIONAR USUARIOS
    //
    @Override
    public EspecUsuario anyadirUsuBDD(EspecUsuario user) throws RemoteException {
        userId = userId +1;
        final UsuarioId uId = new UsuarioId(userId);
        user.setUserId(uId);
        usuarios.put(user.getUserName(), user);
        return user;
    }

    @Override
    public EspecUsuario getUsuBDD(String userName) throws RemoteException {
        return usuarios.get(userName);
    }

    @Override
    public void removeUsuBDD(EspecUsuario especUsuario) throws RemoteException {
        usuarios.remove(especUsuario.getUserName());
    }

    public ConcurrentHashMap<String, EspecUsuario> getUsuarios() {
        return usuarios;
    }
    //
    //METODOS PARA GESTIONAR MERCANCIAS
    //

    @Override
    public ConcurrentHashMap<MercanciaId, EspecMercancia> getMercancias() {
        return mercancias;
    }

    //
    // METODOS PARA GESTIONAR OFERTAS
    //
    @Override
    public EspecOferta anyadirOfertaBDD (EspecOferta especOferta) throws RemoteException {
        ofertaId = ofertaId + 1;
        final OfertaId oId = new OfertaId (ofertaId);
        final EspecOferta especOferta1 = new EspecOferta (oId, especOferta.getEspecMercancia(), especOferta.getPrecio(), especOferta.getKilos(), especOferta.getUsuarioId());
        ofertas.put(oId, especOferta1);
        return especOferta1;
    }

    @Override
    public void removeOfertaBDD (OfertaId ofertaId) throws RemoteException {
        ofertas.remove(ofertaId);
    }

    @Override
    public ConcurrentHashMap<OfertaId, EspecOferta> getOfertasBDD () {return ofertas; }

    @Override
    public EspecDemanda anyadirDemandaBDD (EspecDemanda especDemanda) throws RemoteException {
        demandaId = demandaId +1;
        final DemandaId dId = new DemandaId(demandaId);
        final EspecDemanda especDemanda1 = new EspecDemanda(dId, especDemanda.getEspecMercancia(), especDemanda.getUsuarioId(), especDemanda.getDemandaListener());
        demandas.put(dId, especDemanda1);
        return especDemanda1;
    }

    @Override
    public void removeDemandaBDD(DemandaId demandaId) throws RemoteException {
        demandas.remove(demandaId);
    }

    @Override
    public ConcurrentHashMap<DemandaId, EspecDemanda> getDemandasBDD() throws RemoteException {
        return demandas;
    }

    @Override
    public ConcurrentHashMap<DemandaId, EspecDemanda> getDemandasBDD(UsuarioId usuarioId) throws RemoteException {
        final ConcurrentHashMap<DemandaId, EspecDemanda> demandasTmp = new ConcurrentHashMap<>();
        for (EspecDemanda value:
                demandas.values()
        ) {
            if (value.getUsuarioId().equals(usuarioId)) {
                demandasTmp.put(value.getDemandaId(), value);
            }
        }
        return demandasTmp;
    }
}
