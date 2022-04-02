package es.uned.alumno.aruiz238.interfaz;

import es.uned.alumno.aruiz238.modelo.EspecMercancia;
import es.uned.alumno.aruiz238.modelo.EspecOferta;

import java.util.List;

public class IAdaptadorInterfazGrafica {
    public void escribeEnPantallaEspecMercanciaList (List<EspecMercancia> especMercanciaList) {
        System.out.println("Listado de mercancías existentes: ");
        for (EspecMercancia element: especMercanciaList
             ) {
                    System.out.print(element.getMercanciaId().getTipoId());
                    System.out.print(".- ");
                    System.out.println(element.getDescripcion());
        }
    }
    public void escribeEnPantallaEspecOfertaList (List<EspecOferta> especOfertaList) {
        System.out.println("Listado de ofertas existentes: ");
        for (EspecOferta element: especOfertaList
        ) {
            System.out.println("OfertaId; Descripcion; Kilos; Precio");
            System.out.println("========  ===========  =====  ======");
            System.out.print(element.getOfertaId().getOfertaId());
            System.out.print(".- ");
            System.out.print(element.getEspecMercancia().getDescripcion());
            System.out.print("; ");
            System.out.print(element.getKilos());
            System.out.print(" Kg.");
            System.out.print("; ");
            System.out.print(element.getPrecio());
            System.out.println(" €");
        }
    }
    public void salidaPorPantallaLn (String cadenaSalida) {
        System.out.println(cadenaSalida);
    }
    public void salidaPorPantalla (String cadenaSalida) {
        System.out.print(cadenaSalida);
    }
}
