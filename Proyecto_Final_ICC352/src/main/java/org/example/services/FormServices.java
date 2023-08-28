package org.example.services;

import org.example.encapsulaciones.Form;

public class FormServices extends GestionDB<Form> {
    public static FormServices instancia;

    private FormServices(){super(Form.class);}
    public static  FormServices getInstance(){
        if(instancia==null){
            instancia = new FormServices();
        }
        return instancia;
    }
}
