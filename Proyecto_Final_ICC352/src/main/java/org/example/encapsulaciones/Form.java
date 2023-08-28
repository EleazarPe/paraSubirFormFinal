package org.example.encapsulaciones;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import org.bson.types.ObjectId;

import java.util.ArrayList;
@Entity
public class Form {
    @Id
    private ObjectId id ;
    @Reference
    private ArrayList<Pregunta> mispreguntas;
    @Reference
    private ArrayList<Seleccion> misselecciones;
    @Reference
    private ArrayList<Escala> misescalas;
    @Reference
    private Usuario admUser;
    @Reference
    private ArrayList<Usuario> usuarios;

    public Form(){
        this.mispreguntas = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.misselecciones = new ArrayList<>();
        this.misescalas = new ArrayList<>();
    }

    public ArrayList<Seleccion> getMisselecciones() {
        return misselecciones;
    }

    public void setMisselecciones(ArrayList<Seleccion> misselecciones) {
        this.misselecciones = misselecciones;
    }

    public ArrayList<Escala> getMisescalas() {
        return misescalas;
    }

    public void setMisescalas(ArrayList<Escala> misescalas) {
        this.misescalas = misescalas;
    }

    public Usuario getAdmUser() {
        return admUser;
    }

    public void setAdmUser(Usuario admUser) {
        this.admUser = admUser;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    public  void insertUsuario(Usuario user){
        usuarios.add(user);
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ArrayList<Pregunta> getMispreguntas() {
        return mispreguntas;
    }

    public void setMispreguntas(ArrayList<Pregunta> mispreguntas) {
        this.mispreguntas = mispreguntas;
    }

}
