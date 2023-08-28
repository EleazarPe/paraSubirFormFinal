package org.example.encapsulaciones;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Calendar;

@Entity
public class Clasificador {
    @Id
    ObjectId id;

    @Reference
    ArrayList<DataUser> datos;

    @Reference
    Usuario idusuario;
    public Clasificador(){}
    public Clasificador(ArrayList<DataUser> usuarios, Usuario us){
        this.datos = usuarios;
        this.idusuario = us;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ArrayList<DataUser> getDatos() {
        return datos;
    }

    public void setDatos(ArrayList<DataUser> datos) {
        this.datos = datos;
    }

    public Usuario getUsuario() {
        return idusuario;
    }

    public void setUsuario(Usuario usuario) {
        this.idusuario = usuario;
    }
}
