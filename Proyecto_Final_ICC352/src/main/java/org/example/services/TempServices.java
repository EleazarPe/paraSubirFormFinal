package org.example.services;

import org.example.encapsulaciones.temp;

public class TempServices extends GestionDB<temp> {
    public static TempServices instancia;

    private TempServices(){super(temp.class);}
    public static  TempServices getInstance(){
        if(instancia==null){
            instancia = new TempServices();
        }
        return instancia;
    }
}
