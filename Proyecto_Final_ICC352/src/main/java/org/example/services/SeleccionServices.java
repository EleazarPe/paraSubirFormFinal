package org.example.services;


import org.example.encapsulaciones.Seleccion;

public class SeleccionServices extends GestionDB<Seleccion> {
    public static SeleccionServices instancia;

    private SeleccionServices(){super(Seleccion.class);}
    public static SeleccionServices getInstance(){
        if(instancia==null){
            instancia = new SeleccionServices();
        }
        return instancia;
    }
}
