package org.example.services;

import org.example.encapsulaciones.Escala;

public class EscalaServices extends GestionDB<Escala> {
    public static EscalaServices instancia;

    private EscalaServices(){super(Escala.class);}
    public static EscalaServices getInstance(){
        if(instancia==null){
            instancia = new EscalaServices();
        }
        return instancia;
    }

}
