//////////////////////////////////////////
// Autor: Ángel Ruiz Cantón
// E-mail: aruiz238@alumno.uned.es
//////////////////////////////////////////
package es.uned.alumno.aruiz238.regulador;

import es.uned.alumno.aruiz238.comm.IniciarRMI;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class Regulador extends IniciarRMI {
    public Regulador() {
        super(Regulador.class);
    }

    private static Registry startRegistry () throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(registryPort);
            registry.list();
            return registry;
        } catch (RemoteException e) {
            System.out.println("El registro no se puede localizar en el puerto: " + registryPort);
            Registry registry = LocateRegistry.createRegistry(registryPort);
            System.out.println("El registro se ha creado en el puerto: " + registryPort);
            return registry;
        }
    }

    @Override
    protected void operacionRMI() {
        try {
            Registry registry = startRegistry();

            ServicioDatosImpl servDatos = new ServicioDatosImpl();
            ServicioDatosInterface servicioDatosStub = (ServicioDatosInterface) UnicastRemoteObject.exportObject (servDatos,0);
            registry.rebind (ServicioDatosInterface.NOMBRE_SERVICIO_DATOS, servicioDatosStub);
            System.out.println("Después de hecho el rebind del ServicioDatosInterface");

            ServicioAutenticacionImpl servAutenticacion = new ServicioAutenticacionImpl();
            ServicioAutenticacionInterface servicioAutenticacionStub = (ServicioAutenticacionInterface) UnicastRemoteObject.exportObject(servAutenticacion,0);
            registry.rebind (ServicioAutenticacionInterface.NOMBRE_SERVICIO_AUTENTICACION, servicioAutenticacionStub);
            System.out.println("Después de hecho el rebind del ServicioAutenticacionInterface");

            ServicioMercanciasImpl servMercancias = new ServicioMercanciasImpl();
            ServicioMercanciasInterface servicioMercanciasStub = (ServicioMercanciasInterface) UnicastRemoteObject.exportObject(servMercancias,0);
            registry.rebind (ServicioMercanciasInterface.NOMBRE_SERVICIO_MERCANCIAS, servicioMercanciasStub);
            System.out.println("Después de hecho el rebind del ServicioMercanciasInterface");

            System.out.println("Después de hecho el rebind de todo");
            System.out.println(Arrays.toString(registry.list()));
            System.out.println("El servicio de Datos levantado Ok, intro para terminar...");
            System.in.read();

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
        } catch (IOException e) {
            System.out.println("Ha habido una excepción en el levantamiento del servicio de datos. Excepción: IOException. Detalles: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Regulador();
    }
}
