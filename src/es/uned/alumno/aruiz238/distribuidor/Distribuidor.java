/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.distribuidor;

import es.uned.alumno.aruiz238.excepciones.*;
import es.uned.alumno.aruiz238.interfaz.*;
import es.uned.alumno.aruiz238.modelo.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import static es.uned.alumno.aruiz238.comm.IniciarRMI.registryHost;
import static es.uned.alumno.aruiz238.comm.IniciarRMI.registryPort;

public class Distribuidor implements ServicioVenta {
    // Servicio de venta
    public static final String NOMBRE_SERVICIO_VENTAS = "rmi://" + registryHost + ":" + registryPort + "/servicioventas/";
    private static final List<EspecOferta> ofertasVendidas = new ArrayList<>();
    // Variable de instancia
    private static final String[] opcionesMenuPrincipal = {"Registrar un nuevo usuario.", "Autenticarse en el sistema (hacer login).", "Salir."};
    private static final int numOpsMenuPrincipal = 3;
    private static final String[] opcionesMenuSecundario = {"Introducir oferta.", "Quitar oferta.", "Mostrar ventas.", "Darse de baja en el sistema.", "Salir."};
    private static final int numOpsMenuSecundario = 5;
    private static EspecUsuario especUsuario;

    public Distribuidor() {

    }

    @Override
    public boolean comprarMercancia(EspecOferta especOferta) throws RemoteException {
        ofertasVendidas.add(especOferta);
        return true;
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
    private static void introducirOferta() {
        final  IAdaptadorInterfazGrafica adaptadorInterfazGrafica = FactoriaInterfazGrafica.getInstancia().getAdaptadorInterfazGrafica();
        try {
            //Inicialización
            final CatalogoMercancias ctlgoMer = new CatalogoMercancias();
            final CatalogoOfertas ctlgoOfer = new CatalogoOfertas();
            // Fin de la inicilización
            final ManejadorIntroducirOferta mndor = new ManejadorIntroducirOferta (ctlgoMer, ctlgoOfer, especUsuario.getUserId());

            final List<EspecMercancia> especMercancias = mndor.introducirOferta();
            adaptadorInterfazGrafica.escribeEnPantallaEspecMercanciaList(especMercancias);
            final String tipoMercanciaOfer = EntradaDatosPorConsola.lectura ("Introduzca el tipo de mercancía ofertada: ");
            final String precioMercanciaOfer = EntradaDatosPorConsola.lectura ("Introduzca el precio de la oferta:");
            final String kilosMercanciaOfer = EntradaDatosPorConsola.lectura ("Introduzca los kilos de la oferta: ");
            final EspecOferta especOferta = mndor.altaOferta (new MercanciaId(Integer.parseInt(tipoMercanciaOfer)),Float.parseFloat(precioMercanciaOfer), Float.parseFloat(kilosMercanciaOfer));
            adaptadorInterfazGrafica.salidaPorPantallaLn("Se ha introducido satisfactoriamente la oferta con Id: " + especOferta.getOfertaId().getOfertaId());
            adaptadorInterfazGrafica.salidaPorPantallaLn("Los kilos de la oferta son: " + especOferta.getKilos());
            adaptadorInterfazGrafica.salidaPorPantallaLn("El precio de la oferta es: " + especOferta.getPrecio());
            adaptadorInterfazGrafica.salidaPorPantallaLn("La mercancía de la oferta es: " + especOferta.getEspecMercancia().getDescripcion());
            adaptadorInterfazGrafica.salidaPorPantallaLn("El userId de la oferta es: " + especOferta.getUsuarioId().getUserId());
        } catch (NotBoundException e) {
            adaptadorInterfazGrafica.salidaPorPantallaLn("Ha habido una excepción en la búsqueda del servicio de mercancías. Excepción: NotBoundException. Detalles: " + e.getMessage());
            e.printStackTrace();
        } catch (RemoteException e) {
            adaptadorInterfazGrafica.salidaPorPantallaLn("Ha habido una excepción en la comunicación con el servicio de mercancías. Excepción: RemoteException. Detalles: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static void quitarOferta () {
        final  IAdaptadorInterfazGrafica adaptadorInterfazGrafica = FactoriaInterfazGrafica.getInstancia().getAdaptadorInterfazGrafica();
        try {
            final CatalogoOfertas ctlgoOfer = new CatalogoOfertas();
            //final List<EspecOferta> especOfertas = ctlgoOfer.getOfertas();
            //if (!especOfertas.isEmpty()) {
                //adaptadorInterfazGrafica.escribeEnPantallaEspecOfertaList(especOfertas);
                final ManejadorQuitarOferta mndor = new ManejadorQuitarOferta(ctlgoOfer);
                final String ofertaId = EntradaDatosPorConsola.lectura ("Introduzca el Id de la oferta a borrar: ");
                mndor.quitarOferta (new OfertaId(Integer.parseInt(ofertaId)));
                adaptadorInterfazGrafica.salidaPorPantallaLn("Se ha borrado satisfactoriamente la oferta.");
                //final List<EspecOferta> especOfertas1 = ctlgoOfer.getOfertas();
                //adaptadorInterfazGrafica.escribeEnPantallaEspecOfertaList(especOfertas1);
            //} else {
            //    System.out.println("No existen ofertas en la BDD asociadas al usuario.");
            //}
        } catch (NotBoundException e) {
            adaptadorInterfazGrafica.salidaPorPantallaLn("Ha habido una excepción en la búsqueda del servicio de mercancías. Excepción: NotBoundException. Detalles: " + e.getMessage());
            e.printStackTrace();
        } catch (RemoteException e) {
            adaptadorInterfazGrafica.salidaPorPantallaLn("Ha habido una excepción en la comunicación con el servicio de mercancías. Excepción: RemoteException. Detalles: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static void mostrarVentas () {
        final  IAdaptadorInterfazGrafica adaptadorInterfazGrafica = FactoriaInterfazGrafica.getInstancia().getAdaptadorInterfazGrafica();
        adaptadorInterfazGrafica.escribeEnPantallaEspecOfertaList(ofertasVendidas);
    }
    private static void darseDeBaja () {
        final  IAdaptadorInterfazGrafica adaptadorInterfazGrafica = FactoriaInterfazGrafica.getInstancia().getAdaptadorInterfazGrafica();
        try {
            final CatalogoOfertas ctlgoOfer = new CatalogoOfertas (especUsuario.getUserId());
            final ManejadorDarseDeBaja mndor = new ManejadorDarseDeBaja (ctlgoOfer, especUsuario);
            mndor.darseDeBaja (especUsuario);
            especUsuario = null;
        } catch (NotBoundException e) {
            adaptadorInterfazGrafica.salidaPorPantallaLn ("Ha habido una excepción en la búsqueda del servicio de mercancías. Detalles: " + e.getMessage());
            e.printStackTrace ();
        } catch (RemoteException e) {
            adaptadorInterfazGrafica.salidaPorPantallaLn ("Ha habido una excepción en la comunicación con el servicio de mercancías. Detalles: " + e.getMessage());
            e.printStackTrace ();
        }
    }

    public static void main(String[] args) {
        final Menu menuPrincipal = new Menu (TipoOpcionMenu.DIGITOS, numOpsMenuPrincipal, opcionesMenuPrincipal);
        final Menu menuScundario = new Menu (TipoOpcionMenu.DIGITOS, numOpsMenuSecundario, opcionesMenuSecundario);

        final  IAdaptadorInterfazGrafica adaptadorInterfazGrafica = FactoriaInterfazGrafica.getInstancia().getAdaptadorInterfazGrafica();

        char optMenuPrincipal;
        int idAutenticacion = 0;
        try {
            Registry registry = LocateRegistry.getRegistry (registryPort);
            registry.list();
            Distribuidor distribuidor = new Distribuidor();
            registry.list();
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
                                if (idAutenticacion > 0) {
                                    // Si me autentico correctamente idAutenticacion > 0
                                    // Pongo en marcha el servicio de Venta
                                    System.out.println("El valor del servicio es: " + NOMBRE_SERVICIO_VENTAS + idAutenticacion);
                                    ServicioVenta servicioVentasStub = (ServicioVenta) UnicastRemoteObject.exportObject(distribuidor, 0);
                                    registry.rebind(NOMBRE_SERVICIO_VENTAS + idAutenticacion, servicioVentasStub);
                                    registry.list();
                                }
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
                                    // Pongo en marcha el servicio de Venta
                                    System.out.println("El valor del servicio es: " + NOMBRE_SERVICIO_VENTAS + idAutenticacion);
                                    ServicioVenta servicioVentasStub = (ServicioVenta) UnicastRemoteObject.exportObject(distribuidor, 0);
                                    registry.rebind(NOMBRE_SERVICIO_VENTAS + idAutenticacion, servicioVentasStub);
                                    registry.list();
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
                                    System.out.println ("He tecleado la opción 1: Introducir oferta.");
                                    introducirOferta();
                                }
                                case '2' -> {
                                    System.out.println ("He tecleado la opción 2: Quitar oferta.");
                                    quitarOferta();
                                }
                                case '3' -> {
                                    System.out.println ("He tecleado la opción 3: Mostrar ventas.");
                                    mostrarVentas();
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
            if (idAutenticacion > 0) {
                // Desregistro el servicio de ventas
                System.out.println("Desregistro el servicio de venta.");
                System.out.println("El valor del idAutenticacion es: " + idAutenticacion);
                System.out.println("El valor del servicio es: " + NOMBRE_SERVICIO_VENTAS + idAutenticacion);
                registry.list();
                registry.unbind(NOMBRE_SERVICIO_VENTAS + idAutenticacion);
                UnicastRemoteObject.unexportObject(distribuidor, true);
                registry.list();
                adaptadorInterfazGrafica.salidaPorPantallaLn("Bajado el Servicio de Ventas ...");
            }
            System.out.println("Última instrucción.");
        } catch (Exception e) {
            adaptadorInterfazGrafica.salidaPorPantallaLn("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
