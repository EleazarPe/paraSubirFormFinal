package org.example.services;

import org.example.encapsulaciones.DataUser;

public class DataUserServices extends GestionDB<DataUser> {
    public static DataUserServices instancia;

    private DataUserServices(){super(DataUser.class);}
    public static  DataUserServices getInstance(){
        if(instancia==null){
            instancia = new DataUserServices();
        }
        return instancia;
    }
}
