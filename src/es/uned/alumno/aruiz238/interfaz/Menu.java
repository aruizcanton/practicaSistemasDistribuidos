//////////////////////////////////////////
// Autor: Ángel Ruiz Cantón
// E-mail: aruiz238@alumno.uned.es
//////////////////////////////////////////
package es.uned.alumno.aruiz238.interfaz;

import es.uned.alumno.aruiz238.excepciones.OpcionMenuInvalidaException;

public class Menu {
    TipoOpcionMenu tipoOpcionMenu;
    String[] opcionesMenu;
    int numeroOpciones;
    char[] seleccionMenu;

    public Menu (TipoOpcionMenu tipOpMenu, int numOpciones, String[] opsMenu) {
        tipoOpcionMenu = tipOpMenu;
        numeroOpciones = numOpciones;
        opcionesMenu = opsMenu;

        if (tipoOpcionMenu == TipoOpcionMenu.LETRAS || numeroOpciones > 9) {
            char[] alfabeto = "abcdefghijklmnopqrstuvwxy".toCharArray();
            seleccionMenu = new char[numeroOpciones];
            System.arraycopy(alfabeto, 0, seleccionMenu, 0, numeroOpciones);
        } else {
            seleccionMenu = new char[numeroOpciones];
            for (int i = 0; i < numeroOpciones; ++i) {
                seleccionMenu[i] = (char) (i +'1');
            }
        }
    }
    public void mostrarMenu() {
        for (int i = 0; i < numeroOpciones; ++i) {
            System.out.print(seleccionMenu[i]);
            System.out.print(".- ");
            System.out.println(opcionesMenu[i]);
        }
    }
    public char leerOpcion() throws OpcionMenuInvalidaException {
        char c = EntradaDatosPorConsola.lectura("").charAt(0);
        for (int i = 0; i < numeroOpciones; ++i) {
            if (c == seleccionMenu[i]) {
                return c;
            }
        }
        throw new OpcionMenuInvalidaException ("La opcion tecleada no es válida");
    }
}
