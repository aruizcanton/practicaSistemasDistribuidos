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
     private static int userId=0;
     private static int ofertasId=0;

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
        ofertasId = ofertasId + 1;
        final OfertaId oId = new OfertaId (ofertasId);
        final EspecOferta especOferta1 = new EspecOferta (oId,especOferta.getEspecMercancia(), especOferta.getPrecio(), especOferta.getKilos(), especOferta.getUsuarioId());
        ofertas.put(oId, especOferta1);
        return especOferta1;
    }
    public void removeOfertaBDD (OfertaId ofertaId) throws RemoteException {
        ofertas.remove(ofertaId);
    }
    public ConcurrentHashMap<OfertaId, EspecOferta> getOfertasBDD () {return ofertas; }

    public ConcurrentHashMap<OfertaId, EspecOferta> getOfertasBDD (UsuarioId usuarioId) {
        final ConcurrentHashMap <OfertaId, EspecOferta> ofertasTmp = new ConcurrentHashMap<>();

        for (EspecOferta value:
                ofertas.values()
             ) {
                    if (value.getUsuarioId().equals(usuarioId)) {
                        ofertasTmp.put(value.getOfertaId(), value);
                    }
        }
        return ofertasTmp;
    }
}
