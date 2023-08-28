package org.example.encapsulaciones;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;


@Entity

public class Pregunta {
    @Id
    private ObjectId id;
    private String titulo;
    public Pregunta(){}
    public Pregunta( String titulo){

        this.setTitulo(titulo);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
