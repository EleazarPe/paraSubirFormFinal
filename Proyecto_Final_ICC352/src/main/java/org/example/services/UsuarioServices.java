package org.example.services;

import org.example.encapsulaciones.Usuario;

public class UsuarioServices extends GestionDB<Usuario> {
    public static UsuarioServices instancia;

    private UsuarioServices(){super(Usuario.class);}
    public static  UsuarioServices getInstance(){
        if(instancia==null){
            instancia = new UsuarioServices();
        }
        return instancia;
    }
}
