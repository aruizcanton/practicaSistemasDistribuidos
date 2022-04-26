/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.cliente;

import es.uned.alumno.aruiz238.excepciones.*;
import es.uned.alumno.aruiz238.interfaz.*;
import es.uned.alumno.aruiz238.modelo.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Cliente implements DemandaListener {
    // Variables de instancia
    private static final String[] opcionesMenuPrincipal = {"Registrar un nuevo usuario.", "Autenticarse en el sistema (hacer login).", "Salir."};
    private static final int numOpsMenuPrincipal = 3;
    private static final String[] opcionesMenuSecundario = {"Introducir demanda.", "Recibir ofertas.", "Comprar mercancía.", "Darse de baja en el sistema.", "Ir al menú anterior."};
    private static final int numOpsMenuSecundario = 5;
    private static EspecUsuario especUsuario;
    private static List<EspecOferta> ofertasDemandadas;

    public Cliente() throws RemoteException {
    }

    @Override
    public void nuevaOfertaRecibida(EspecOferta especOferta) throws RemoteException {
        final IAdaptadorInterfazGrafica adaptadorInterfazGrafica = FactoriaInterfazGrafica.getInstancia().getAdaptadorInterfazGrafica();
        adaptadorInterfazGrafica.escribeEnPantallaOfertaRecibida(especOferta);
        //System.out.println("He recibido una nueva oferta. Yiuhu");
        //System.out.println("El id del producto ofertado es: " + especOferta.);
        //System.out.println("La descripción dle producto ofertado es: " + descripcion);
    }

    private static int registrarUsuario () {
        try {
            final String userName = EntradaDatosPorConsola.lectura ("Nombre de usuario:");
            final String password = EntradaDatosPorConsola.lectura ("Password:");
            final ManejadorRegistrarUsuario manejadorRegistrarUsuario = new ManejadorRegistrarUsuario();
            especUsuario = manejadorRegistrarUsuario.registarUsuario(userName, password);
            final UsuarioId usuarioId = especUsuario.getUserId ();
            return usuarioId.getUserId ();
        } catch (UserExistBDDException e) {
            System.out.println ("El usuario ya está registrado. Autentíquese en el sistema (haga login).");
            return -1;
        } catch (UserPassFormatException e) {
            System.out.println ("El usuario y la password han de estar rellenas.");
            return -2;
        } catch (RemoteException e) {
            System.out.println ("Error de comunicación en el servicio de autenticación. " + e.getMessage());
            return -3;
        } catch (NotBoundException e) {
            System.out.println ("Error al no encontrar el servicio de autenticación en el registro de RMI.");
            return -4;
        }
    }
    private static int iniciarSesion () {
        try {
            final String userName = EntradaDatosPorConsola.lectura("Nombre de usuario:");
            final String password = EntradaDatosPorConsola.lectura("Password:");
            System.out.println("El usuario es: #" + userName + "#");
            System.out.println("La password es: #" + password + "#");
            final ManejadorInicioSesion manejadorInicioSesion = new ManejadorInicioSesion();
            especUsuario = manejadorInicioSesion.IniciarSesion(userName, password);
            final UsuarioId usuarioId = especUsuario.getUserId();
            // Registro del servicio de venta

            return usuarioId.getUserId();
        } catch (PasswordIncorrectException e) {
            System.out.println("La password introducida no es correcta.");
            return -1;
        } catch (UserNameNotRegistredException e){
            System.out.println("El username no se encuentra registrado en el sistema.");
            return -2;
        } catch (UserPassFormatException e) {
            System.out.println("El usuario y la password han de estar rellenas.");
            return -3;
        } catch (RemoteException e) {
            System.out.println("Error de comunicación en el servicio de autenticación. " + e.getMessage());
            return -4;
        } catch (NotBoundException e) {
            System.out.println("Error al no encontrar el servicio de autenticación en el registro de RMI.");
            return -5;
        }
    }
    private static void introducirDemanda (DemandaListener demandaListener) {
        final  IAdaptadorInterfazGrafica adaptadorInterfazGrafica = FactoriaInterfazGrafica.getInstancia().getAdaptadorInterfazGrafica();

        try {
            //Inicialización
            final CatalogoMercancias ctlgoMer = new CatalogoMercancias ();
            final CatalogoDemandas ctlgoDeman = new CatalogoDemandas (especUsuario.getUserId());
            // Fin de la inicilización
            final ManejadorIntroducirDemanda mndor = new ManejadorIntroducirDemanda (ctlgoMer, ctlgoDeman);
            final List<EspecMercancia> especMercancias = mndor.introducirDemanda();
            adaptadorInterfazGrafica.escribeEnPantallaEspecMercanciaList(especMercancias);
            final String tipoMercanciaDeman = EntradaDatosPorConsola.lectura ("Introduzca el tipo de mercancía demandada: ");
            final EspecDemanda especDemanda = mndor.altaDemanda(new MercanciaId(Integer.parseInt(tipoMercanciaDeman)), especUsuario.getUserId(), demandaListener);

            adaptadorInterfazGrafica.salidaPorPantallaLn("Se ha introducido satisfactoriamente la demanda con Id: " + especDemanda.getDemandaId().getDemadaId());
            adaptadorInterfazGrafica.salidaPorPantallaLn("El tipo de la mercancía de la demanda es: " + especDemanda.getEspecMercancia().getMercanciaId().getTipoId());
            adaptadorInterfazGrafica.salidaPorPantallaLn("La mercancía de la demanda es: " + especDemanda.getEspecMercancia().getDescripcion());
            adaptadorInterfazGrafica.salidaPorPantallaLn("El userId de la demanda es: " + especDemanda.getUsuarioId().getUserId());
            EntradaDatosPorConsola.lectura("Pulse una tecla para continuar...");
            adaptadorInterfazGrafica.salidaPorPantallaLn("");
        } catch (NotBoundException e) {
            adaptadorInterfazGrafica.salidaPorPantallaLn("Ha habido una excepción en la búsqueda del servicio de mercancías. Excepción: NotBoundException. Detalles: " + e.getMessage());
            e.printStackTrace();
        } catch (RemoteException e) {
            adaptadorInterfazGrafica.salidaPorPantallaLn("Ha habido una excepción en la comunicación con el servicio de mercancías. Excepción: RemoteException. Detalles: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static void recibirOfertas () {
        final  IAdaptadorInterfazGrafica adaptadorInterfazGrafica = FactoriaInterfazGrafica.getInstancia().getAdaptadorInterfazGrafica();
        try {
            final CatalogoDemandas catalogoDemandas = new CatalogoDemandas (especUsuario.getUserId());
            final CatalogoOfertas catalogoOfertas = new CatalogoOfertas ();
            final ManejadorRecibirOfertas mndor = new ManejadorRecibirOfertas (catalogoDemandas, catalogoOfertas, especUsuario.getUserId());
            ofertasDemandadas = mndor.recibirOfertas();
            adaptadorInterfazGrafica.escribeEnPantallaEspecOfertaListDemandadas(ofertasDemandadas);
            EntradaDatosPorConsola.lectura("Pulse una tecla para continuar...");
            adaptadorInterfazGrafica.salidaPorPantallaLn("");
        } catch (NotBoundException e) {
            adaptadorInterfazGrafica.salidaPorPantallaLn("Ha habido una excepción en la búsqueda del servicio de mercancías. Excepción: NotBoundException. Detalles: " + e.getMessage());
            e.printStackTrace();
        } catch (RemoteException e) {
            adaptadorInterfazGrafica.salidaPorPantallaLn("Ha habido una excepción en la comunicación con el servicio de mercancías. Excepción: RemoteException. Detalles: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static void comprarMercancia () {
        final  IAdaptadorInterfazGrafica adaptadorInterfazGrafica = FactoriaInterfazGrafica.getInstancia().getAdaptadorInterfazGrafica();
        final  ManejadorComprarMercancia mndor = new ManejadorComprarMercancia(ofertasDemandadas);

        final String ofertaIdParComprar = EntradaDatosPorConsola.lectura ("Introduzca el identificador de la oferta que desea comprar: ");
        final OfertaId ofertaId = new OfertaId(Integer.parseInt(ofertaIdParComprar));
        try {
            if (mndor.comprarMercancia(ofertaId)) {
                adaptadorInterfazGrafica.salidaPorPantallaLn("La compra se ha realizado de manera satisfactoria.");
            } else {
                adaptadorInterfazGrafica.salidaPorPantallaLn("La compra no se ha podido realizar. Inténtelo de nuevo.");
            }
        } catch (NotBoundException e) {
            adaptadorInterfazGrafica.salidaPorPantallaLn("Ha habido una excepción en la búsqueda del servicio de mercancías. Excepción: NotBoundException. Detalles: " + e.getMessage());
            e.printStackTrace();
        } catch (RemoteException e) {
            adaptadorInterfazGrafica.salidaPorPantallaLn("Ha habido una excepción en la comunicación con el servicio de mercancías. Excepción: RemoteException. Detalles: " + e.getMessage());
            e.printStackTrace();
        }
        EntradaDatosPorConsola.lectura("Pulse una tecla para continuar ...");
        adaptadorInterfazGrafica.salidaPorPantallaLn("");
    }
    private static void darseDeBaja() {
        final  IAdaptadorInterfazGrafica adaptadorInterfazGrafica = FactoriaInterfazGrafica.getInstancia().getAdaptadorInterfazGrafica();
        try {
            final CatalogoDemandas ctlgoDeman = new CatalogoDemandas(especUsuario.getUserId());
            final ManejadorDarseDeBaja mndor = new ManejadorDarseDeBaja (ctlgoDeman);
            mndor.darseDeBaja(especUsuario);
            especUsuario = null;
            adaptadorInterfazGrafica.salidaPorPantallaLn("El usuario se ha dado de baja en el sistema.");
            EntradaDatosPorConsola.lectura("Pulse una tecla para continuar...");
            adaptadorInterfazGrafica.salidaPorPantallaLn("");
        } catch (NotBoundException e) {
            adaptadorInterfazGrafica.salidaPorPantallaLn ("Ha habido una excepción en la búsqueda del servicio de mercancías. Detalles: " + e.getMessage());
            e.printStackTrace();
        } catch (RemoteException e) {
            adaptadorInterfazGrafica.salidaPorPantallaLn ("Ha habido una excepción en la comunicación con el servicio de mercancías. Detalles: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final Menu menuPrincipal = new Menu (TipoOpcionMenu.DIGITOS, numOpsMenuPrincipal, opcionesMenuPrincipal);
        final Menu menuScundario = new Menu (TipoOpcionMenu.DIGITOS, numOpsMenuSecundario, opcionesMenuSecundario);

        final IAdaptadorInterfazGrafica adaptadorInterfazGrafica = FactoriaInterfazGrafica.getInstancia().getAdaptadorInterfazGrafica();

        char optMenuPrincipal;
        int idAutenticacion = 0;
        try {
            Cliente cliente = new Cliente();
            DemandaListener demandaListenerStub = (DemandaListener) UnicastRemoteObject.exportObject (cliente, 0);
            do {
                menuPrincipal.mostrarMenu();
                optMenuPrincipal = '0';
                try {
                    optMenuPrincipal = menuPrincipal.leerOpcion();
                    switch (optMenuPrincipal) {
                        case '1' -> {
                            System.out.println("He tecleado la opción 1.");
                            if (idAutenticacion <= 0) {
                                // El usuario aun no se ha autenticado
                                idAutenticacion = registrarUsuario();
                                System.out.println ("El id de usuario es: " + idAutenticacion);
                            } else {
                                adaptadorInterfazGrafica.salidaPorPantallaLn("El usuario ya está autenticado. Debe salir de la aplicación para volver a autenticarse.");
                            }
                        }
                        case '2' -> {
                            System.out.println("He tecleado la opción 2.");
                            if (idAutenticacion <= 0) {
                                idAutenticacion = iniciarSesion();
                                if (idAutenticacion > 0) {
                                    // Si me autentico correctamente idAutenticacion > 0
                                    System.out.println("El id de usuario es: " + idAutenticacion);
                                }
                            } else {
                                adaptadorInterfazGrafica.salidaPorPantallaLn("El usuario ya está autenticado. Debe salir de la aplicación para volver a autenticarse.");
                            }
                        }
                    }
                } catch (OpcionMenuInvalidaException e) {
                    adaptadorInterfazGrafica.salidaPorPantallaLn("Opción no válida.");
                }
                if ((optMenuPrincipal == '1' || optMenuPrincipal == '2') && (idAutenticacion > 0)) {
                    char optMenuScundario;
                    do {
                        menuScundario.mostrarMenu();
                        optMenuScundario = '0';
                        try {
                            optMenuScundario = menuScundario.leerOpcion();
                            switch (optMenuScundario) {
                                case '1' -> {
                                    System.out.println ("He tecleado la opción 1: Introducir demanda.");
                                    introducirDemanda(demandaListenerStub);
                                }
                                case '2' -> {
                                    System.out.println ("He tecleado la opción 2: Recibir ofertas.");
                                    recibirOfertas();
                                }
                                case '3' -> {
                                    System.out.println ("He tecleado la opción 3: Comprar mercancía.");
                                    comprarMercancia();
                                }
                                case '4' -> {
                                    System.out.println ("He tecleado la opción 4: Darse de baja en el sistema.");
                                    darseDeBaja();
                                    optMenuScundario = '5';
                                    optMenuPrincipal = '3';
                                }
                            }
                        } catch (OpcionMenuInvalidaException e) {
                            adaptadorInterfazGrafica.salidaPorPantallaLn("Opción no válida.");
                        }
                    } while (optMenuScundario != '5');
                }
            } while (optMenuPrincipal != '3');
            System.out.println("He salido del bucle del menu principal");
            UnicastRemoteObject.unexportObject(cliente, true);
        } catch (Exception e) {
            adaptadorInterfazGrafica.salidaPorPantallaLn("Error: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
