package org.example.services;

import org.example.encapsulaciones.Clasificador;
import org.example.encapsulaciones.DataUser;
import org.example.util.BaseControlador;

public class ClasificadorServices extends GestionDB<Clasificador> {

    public static ClasificadorServices instancia;

    private ClasificadorServices(){super(Clasificador.class);}
    public static  ClasificadorServices getInstance(){
        if(instancia==null){
            instancia = new ClasificadorServices();
        }
        return instancia;
    }
}
