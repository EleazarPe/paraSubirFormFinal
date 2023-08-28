package org.example.services;

import org.example.encapsulaciones.Respuesta;

public class RespuestaServices extends GestionDB<Respuesta> {
public static RespuestaServices instancia;

private RespuestaServices(){super(Respuesta.class);}
public static  RespuestaServices getInstance(){
        if(instancia==null){
        instancia = new RespuestaServices();
        }
        return instancia;
        }
}
