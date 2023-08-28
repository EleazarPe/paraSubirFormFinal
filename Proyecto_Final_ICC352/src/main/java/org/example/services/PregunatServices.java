package org.example.services;

import org.example.encapsulaciones.Pregunta;

public class PregunatServices extends GestionDB<Pregunta> {
    public static PregunatServices instancia;

    private PregunatServices(){super(Pregunta.class);}
    public static  PregunatServices getInstance(){
        if(instancia==null){
            instancia = new PregunatServices();
        }
        return instancia;
    }
}
