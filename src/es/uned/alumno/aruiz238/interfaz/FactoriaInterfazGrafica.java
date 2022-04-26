/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.interfaz;

public class FactoriaInterfazGrafica {
    private static FactoriaInterfazGrafica instancia;
    private static IAdaptadorInterfazGrafica adaptadorInterfazGrafica;

    private FactoriaInterfazGrafica() {

    }
    public static synchronized FactoriaInterfazGrafica getInstancia() {
        if (instancia == null) {
            instancia = new FactoriaInterfazGrafica();
        }
        return instancia;
    }

    public synchronized IAdaptadorInterfazGrafica getAdaptadorInterfazGrafica() {
        if (adaptadorInterfazGrafica == null) {
            adaptadorInterfazGrafica = new IAdaptadorInterfazGrafica();
        }
        return adaptadorInterfazGrafica;
    }
}
