/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.interfaz;


public class FactoriaDeServicios {
    private static FactoriaDeServicios instancia;
    private IAdaptadorServMercancias adaptadorServMercancias;
    private IAdaptadorServAutenticacion adaptadorServAutenticacion;
    private IAdaptadorSerCompra adaptadorSerCompra;

    private FactoriaDeServicios() {

    }

    public static synchronized FactoriaDeServicios getInstancia() {
        if (instancia == null) {
            instancia = new FactoriaDeServicios();
        }
        return instancia;
    }

    public synchronized IAdaptadorServMercancias getAdaptadorServMercancias()  {
        if (adaptadorServMercancias == null) {
            adaptadorServMercancias = new IAdaptadorServMercancias();
        }
        return adaptadorServMercancias;
    }
    public  synchronized IAdaptadorServAutenticacion getAdaptadorServAutenticacion() {
        if (adaptadorServAutenticacion == null) {
            adaptadorServAutenticacion = new IAdaptadorServAutenticacion();
        }
        return adaptadorServAutenticacion;
    }
    public synchronized IAdaptadorSerCompra getAdaptadorSerCompra() {
        if (adaptadorSerCompra == null) {
            adaptadorSerCompra = new IAdaptadorSerCompra();
        }
        return adaptadorSerCompra;
    }
}
