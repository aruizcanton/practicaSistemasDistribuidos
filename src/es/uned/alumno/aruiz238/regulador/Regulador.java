//////////////////////////////////////////
// Autor: Ángel Ruiz Cantón
// E-mail: aruiz238@alumno.uned.es
//////////////////////////////////////////
package es.uned.alumno.aruiz238.regulador;

import es.uned.alumno.aruiz238.comm.IniciarRMI;
import es.uned.alumno.aruiz238.excepciones.OpcionMenuInvalidaException;
import es.uned.alumno.aruiz238.interfaz.*;
import es.uned.alumno.aruiz238.modelo.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;

public class Regulador extends IniciarRMI {
    private static final String[] opcionesMenuSecundario = {"Listar ofertas actuales.", "Listar demandas actuales.", "Listar clientes.", "Listar distribuidores.", "Salir."};
    private static final int numOpsMenuSecundario = 5;

    public Regulador() {
        super(Regulador.class);
    }

    private static Registry startRegistry () throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(registryPort);
            registry.list();
            return registry;
        } catch (RemoteException e) {
            Registry registry = LocateRegistry.createRegistry(registryPort);
            System.out.println("El registro se ha creado en el puerto: " + registryPort);
            return registry;
        }
    }
    private void listarOfertasActuales() {
        final  IAdaptadorInterfazGrafica adaptadorInterfazGrafica = FactoriaInterfazGrafica.getInstancia().getAdaptadorInterfazGrafica();
        try {
            final CatalogoOfertas ctlgoOfer = new CatalogoOfertas();
            final ManejadorListarOfertasActuales mndor = new ManejadorListarOfertasActuales(ctlgoOfer);
            final List<EspecOferta> especOfertas = mndor.getOfertasActuales();
            adaptadorInterfazGrafica.escribeEnPantallaEspecOfertaListActual(especOfertas);
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
    public void listarDemandasActuales() {
        final  IAdaptadorInterfazGrafica adaptadorInterfazGrafica = FactoriaInterfazGrafica.getInstancia().getAdaptadorInterfazGrafica();
        try {
            final CatalogoDemandas ctlgoDeman = new CatalogoDemandas();
            final ManejadorListarDemandas mndor = new ManejadorListarDemandas(ctlgoDeman);
            final List<EspecDemanda> especDemandas = mndor.getDemandasActuales();
            adaptadorInterfazGrafica.escribeEnPantallaEspecDemandaListActual(especDemandas);
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
    public void listarClientes() {
        final  IAdaptadorInterfazGrafica adaptadorInterfazGrafica = FactoriaInterfazGrafica.getInstancia().getAdaptadorInterfazGrafica();
        try {
            final CatalogoUsuarios ctlgoUsu = new CatalogoUsuarios();
            final List<EspecUsuario> especUsuarioList = ctlgoUsu.getUsuarios(TipoUsuEnum.CLIENTE);
            adaptadorInterfazGrafica.escribeEnPantallaClienteList(especUsuarioList);
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
    public void listarDistribuidores() {
        final  IAdaptadorInterfazGrafica adaptadorInterfazGrafica = FactoriaInterfazGrafica.getInstancia().getAdaptadorInterfazGrafica();
        try {
            final CatalogoUsuarios ctlgoUsu = new CatalogoUsuarios();
            final List<EspecUsuario> especUsuarioList = ctlgoUsu.getUsuarios(TipoUsuEnum.DISTRIBUIDOR);
            adaptadorInterfazGrafica.escribeEnPantallaDistribuidorList(especUsuarioList);
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

    @Override
    protected void operacionRMI() {
        final Menu menuScundario = new Menu (TipoOpcionMenu.DIGITOS, numOpsMenuSecundario, opcionesMenuSecundario);
        final IAdaptadorInterfazGrafica adaptadorInterfazGrafica = FactoriaInterfazGrafica.getInstancia().getAdaptadorInterfazGrafica();

        try {
            Registry registry = startRegistry();

            ServicioDatosImpl servDatos = new ServicioDatosImpl();
            ServicioDatosInterface servicioDatosStub = (ServicioDatosInterface) UnicastRemoteObject.exportObject (servDatos,0);
            registry.rebind (ServicioDatosInterface.NOMBRE_SERVICIO_DATOS, servicioDatosStub);
            System.out.println("Servicio de datos levantado OK...");

            ServicioAutenticacionImpl servAutenticacion = new ServicioAutenticacionImpl();
            ServicioAutenticacionInterface servicioAutenticacionStub = (ServicioAutenticacionInterface) UnicastRemoteObject.exportObject(servAutenticacion,0);
            registry.rebind (ServicioAutenticacionInterface.NOMBRE_SERVICIO_AUTENTICACION, servicioAutenticacionStub);
            System.out.println("Servicio de autenticación levantado OK...");

            ServicioMercanciasImpl servMercancias = new ServicioMercanciasImpl();
            ServicioMercanciasInterface servicioMercanciasStub = (ServicioMercanciasInterface) UnicastRemoteObject.exportObject(servMercancias,0);
            registry.rebind (ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS, servicioMercanciasStub);
            System.out.println("Servicio de mercancías levantado OK...");

            System.out.println(Arrays.toString(registry.list()));
            adaptadorInterfazGrafica.salidaPorPantallaLn("");
            char optMenuScundario;
            do {
                menuScundario.mostrarMenu();
                optMenuScundario = '0';
                try {
                    optMenuScundario = menuScundario.leerOpcion();
                    switch (optMenuScundario) {
                        case '1' -> {
                            System.out.println ("He tecleado la opción 1: Listar ofertas actuales.");
                            listarOfertasActuales();
                        }
                        case '2' -> {
                            System.out.println ("He tecleado la opción 2: Listar demandas actuales.");
                            listarDemandasActuales();
                        }
                        case '3' -> {
                            System.out.println ("He tecleado la opción 3: Listar clientes.");
                            listarClientes();
                        }
                        case '4' -> {
                            System.out.println ("He tecleado la opción 4: Listar distribuidores.");
                            listarDistribuidores();
                        }
                    }
                } catch (OpcionMenuInvalidaException e) {
                    adaptadorInterfazGrafica.salidaPorPantallaLn("Opción no válida.");
                }
            } while (optMenuScundario != '5');

            registry.unbind(ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
            UnicastRemoteObject.unexportObject(servDatos, true);
            System.out.println("Bajado el Servicio de Datos ...");
            registry.unbind(ServicioAutenticacionInterface.NOMBRE_SERVICIO_AUTENTICACION);
            UnicastRemoteObject.unexportObject(servAutenticacion, true);
            System.out.println("Bajado el Servicio de Autenticación ...");
            registry.unbind(ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS);
            UnicastRemoteObject.unexportObject(servMercancias, true);
            System.out.println("Bajado el Servicio de Mercancías ...");

            UnicastRemoteObject.unexportObject(registry, true);
            System.out.println("Bajado el registry...");
        } catch (RemoteException e) {
            System.out.println("Ha habido una excepción en el levantamiento del servicio de datos. Excepción: RemoteException. Detalles: " + e.getMessage());
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.out.println("Ha habido una excepción en el levantamiento del servicio de datos. Excepción: NotBoundException. Detalles: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Regulador();
    }
}
