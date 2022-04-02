//////////////////////////////////////////
// Autor: Ángel Ruiz Cantón
// E-mail: aruiz238@alumno.uned.es
//////////////////////////////////////////

package es.uned.alumno.aruiz238.regulador;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;


public class ServicioDatosServer {
    private static final int registryPort = 2002;

    public ServicioDatosServer () {
    }
    private static Registry startRegistry () throws  RemoteException {
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
    public static void main (String[] args) {
        try {
            System.out.println("Comienzo operacionRMI");
            Registry registry = startRegistry();
            System.out.println("Después de arrancar el registro");
            ServicioDatosImpl servicioDatos = new ServicioDatosImpl();
            System.out.println("Después de creada la instancia de servicioDatos");
            ServicioDatosInterface servicioDatosStub = (ServicioDatosInterface) UnicastRemoteObject.exportObject(servicioDatos, 0);
            System.out.println("Después de creado el stub");
            registry.rebind(ServicioDatosInterface.NOMBRE_SERVICIO_DATOS, servicioDatosStub);
            System.out.println("Después de hecho el rebind");
            System.out.println(Arrays.toString(registry.list()));
            System.out.println("Servidor de Ficheros levantado Ok, intro para terminar...");
            System.in.read();

            registry.unbind(ServicioDatosInterface.NOMBRE_SERVICIO_DATOS);
            UnicastRemoteObject.unexportObject(servicioDatos, true);
            UnicastRemoteObject.unexportObject(registry, true);
        } catch (RemoteException e) {
            System.out.println("Ha habido una excepción en el levantamiento del servicio de datos. Excepción: RemoteException. Detalles:");
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.out.println("Ha habido una excepción en el levantamiento del servicio de datos. Excepción: NotBoundException. Detalles:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Ha habido una excepción en el levantamiento del servicio de datos. Excepción: IOException. Detalles:");
            e.printStackTrace();
        }
    }
}
