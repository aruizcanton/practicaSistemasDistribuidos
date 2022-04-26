/*
 ////////////////////////////////////////
  Autor: Ángel Ruiz Cantón
  E-mail: aruiz238@alumno.uned.es
 ////////////////////////////////////////
*/
package es.uned.alumno.aruiz238.interfaz;

import es.uned.alumno.aruiz238.modelo.EspecDemanda;
import es.uned.alumno.aruiz238.modelo.EspecMercancia;
import es.uned.alumno.aruiz238.modelo.EspecOferta;
import es.uned.alumno.aruiz238.modelo.EspecUsuario;

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
        System.out.println("Listado de ofertas vendidas: ");
        System.out.println("OfertaId; Descripcion; Kilos; Precio");
        System.out.println("========  ===========  =====  ======");
        for (EspecOferta element: especOfertaList
        ) {
            System.out.print(element.getOfertaId().getOfertaId());
            System.out.print("; ");
            System.out.print(element.getEspecMercancia().getDescripcion());
            System.out.print("; ");
            System.out.print(element.getKilos());
            System.out.print(" Kg.");
            System.out.print("; ");
            System.out.print(element.getPrecio());
            System.out.println(" €");
        }
    }
    public void escribeEnPantallaEspecOfertaListActual (List<EspecOferta> especOfertaList) {
        System.out.println("Listado de ofertas actuales: ");
        System.out.println("OfertaId; Descripcion; Kilos; Precio");
        System.out.println("========  ===========  =====  ======");
        for (EspecOferta element: especOfertaList
        ) {
            System.out.print(element.getOfertaId().getOfertaId());
            System.out.print("; ");
            System.out.print(element.getEspecMercancia().getDescripcion());
            System.out.print("; ");
            System.out.print(element.getKilos());
            System.out.print(" Kg.");
            System.out.print("; ");
            System.out.print(element.getPrecio());
            System.out.println(" €");
        }
    }
    public void escribeEnPantallaEspecDemandaListActual (List<EspecDemanda> especDemandaList) {
        System.out.println("Listado de demandas actuales: ");
        System.out.println("DemandaId; Mercancía; ClienteId");
        System.out.println("=========  =========  =========");
        for (EspecDemanda element: especDemandaList
        ) {
            System.out.print(element.getDemandaId().getDemadaId());
            System.out.print("; ");
            System.out.print(element.getEspecMercancia().getMercanciaId().getTipoId());
            System.out.print("; ");
            System.out.print(element.getEspecMercancia().getDescripcion());
            System.out.print("; ");
            System.out.println(element.getUsuarioId().getUserId());
        }
    }

    public void escribeEnPantallaEspecOfertaListDemandadas (List<EspecOferta> especOfertaList) {
        System.out.println("Listado de ofertas que se ajustan a las demandas introducidas:");
        System.out.println("OfertaId; Descripcion; Kilos; Precio");
        System.out.println("========= ============ ====== ======");
        for (EspecOferta element: especOfertaList) {
            System.out.print(element.getOfertaId().getOfertaId());
            System.out.print("; ");
            System.out.print(element.getEspecMercancia().getDescripcion());
            System.out.print("; ");
            System.out.print(element.getKilos());
            System.out.print(" Kg.");
            System.out.print("; ");
            System.out.print(element.getPrecio());
            System.out.println(" €");
        }
        System.out.println("");
    }
    public void escribeEnPantallaClienteList (List<EspecUsuario> especUsuarioList) {
        System.out.println("Listado de clientes:");
        System.out.println("UsuarioId; Username");
        System.out.println("=========  ========");
        for (EspecUsuario element: especUsuarioList) {
            System.out.print(element.getUserId().getUserId());
            System.out.print("; ");
            System.out.println(element.getUserName());
        }
    }
    public void escribeEnPantallaDistribuidorList (List<EspecUsuario> especUsuarioList) {
        System.out.println("Listado de distribuidores:");
        System.out.println("UsuarioId; Username");
        System.out.println("=========  ========");
        for (EspecUsuario element: especUsuarioList) {
            System.out.print(element.getUserId().getUserId());
            System.out.print("; ");
            System.out.println(element.getUserName());
        }
    }
    public void escribeEnPantallaOfertaRecibida(EspecOferta especOferta) {
        System.out.println("OfertaId; Descripcion; Kilos; Precio");
        System.out.println("========= ============ ====== ======");
        System.out.print(especOferta.getOfertaId().getOfertaId());
        System.out.print("; ");
        System.out.print(especOferta.getEspecMercancia().getDescripcion());
        System.out.print("; ");
        System.out.print(especOferta.getKilos());
        System.out.print(" Kg.");
        System.out.print("; ");
        System.out.print(especOferta.getPrecio());
        System.out.println(" €");
        System.out.println("");

    }

    public void salidaPorPantallaLn (String cadenaSalida) {
        System.out.println(cadenaSalida);
    }
    public void salidaPorPantalla (String cadenaSalida) {
        System.out.print(cadenaSalida);
    }
}
