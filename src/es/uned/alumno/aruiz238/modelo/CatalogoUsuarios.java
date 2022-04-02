package es.uned.alumno.aruiz238.modelo;

import es.uned.alumno.aruiz238.interfaz.IAdaptadorServAutenticacion;

import java.util.concurrent.ConcurrentHashMap;

public class CatalogoUsuarios {
    private ConcurrentHashMap<UsuarioId, EspecUsuario> especsUsu;
    private IAdaptadorServAutenticacion servAutenticacion;

}
