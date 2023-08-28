package org.example.encapsulaciones;

import java.util.ArrayList;

public class Datastorage {
    private static Datastorage instancia;
    private ArrayList<Form> formularios;
    private ArrayList<DataUser> datosUsuario;
    public Datastorage() {
        super();
        this.formularios = new ArrayList<>();
        this.datosUsuario = new ArrayList<>();

    }
    public static Datastorage getInstancia(){
        if(instancia==null){
            instancia = new Datastorage();
        }
        return instancia;
    }

    public ArrayList<Form> getFormularios() {
        return formularios;
    }

    public void setFormularios(ArrayList<Form> formularios) {
        this.formularios = formularios;
    }

    public ArrayList<DataUser> getDatosUsuario() {
        return datosUsuario;
    }

    public void setDatosUsuario(ArrayList<DataUser> datosUsuario) {
        this.datosUsuario = datosUsuario;
    }
    public int crearIdFormulario(){
        int temp =0;
        if(formularios.isEmpty()){
            return temp;
        }else{
            for (Form i: formularios
                 ) {
                temp++;
            }
        }
        return temp;
    }
    public int crearIdData(){
        int temp =0;
        if(datosUsuario.isEmpty()){
            return temp;
        }else{
            for (DataUser i: datosUsuario
            ) {
                temp++;
            }
        }
        return temp;
    }
    public void insertarFormulario(Form ff){
        formularios.add(ff);
    }
    public void insertarPreguntaToUser(DataUser data){
        datosUsuario.add(data);
    }



}
