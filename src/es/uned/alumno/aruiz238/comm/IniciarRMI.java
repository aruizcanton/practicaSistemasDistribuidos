//////////////////////////////////////////
// Autor: Ángel Ruiz Cantón
// E-mail: aruiz238@alumno.uned.es
//////////////////////////////////////////

package es.uned.alumno.aruiz238.comm;

public abstract class IniciarRMI {
    public static final String registryHost = "localhost";
    public static final int registryPort = 2002;
    public IniciarRMI (Class <?> clase) {
        /*
        System.setProperty("java.security.policy", PoliticaSeguridad.getLocationOfPolicyFile());
        if (System.getSecurityManager() == null) {
            System.setSecurityManager( new SecurityManager());
        }
        */
        System.setProperty("java.rmi.server.codebase",
                clase.getProtectionDomain().getCodeSource().getLocation().toString());
        operacionRMI();
    }
    protected abstract void operacionRMI();
}
