package org.example.encapsulaciones;


import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import org.bson.types.ObjectId;

@Entity
public class DataUser {
    //falta data User
    @Id
    private ObjectId idData;
    @Reference
    private Form idForm;
    @Reference
    private Pregunta idpregunta;
    @Reference
    private Seleccion idseleccion;

    @Reference
    private Escala idescala;
    @Reference
    private Usuario idusuario;

    private String respuesta;

    public DataUser(){

    }
    public DataUser(Form form, Pregunta pregunta, Seleccion seleccion, Escala escala, Usuario user, String respuesta){
        this.idForm = form;
        this.idpregunta = pregunta;
        this.idseleccion = seleccion;
        this.idescala = escala;
        this.idusuario = user;
        this.respuesta = respuesta;
    }


    public Seleccion getIdseleccion() {
        return idseleccion;
    }

    public void setIdseleccion(Seleccion idseleccion) {
        this.idseleccion = idseleccion;
    }

    public Escala getIdescala() {
        return idescala;
    }

    public void setIdescala(Escala idescala) {
        this.idescala = idescala;
    }

    public ObjectId getIdData() {
        return idData;
    }

    public void setIdData(ObjectId idData) {
        this.idData = idData;
    }

    public Form getIdForm() {
        return idForm;
    }

    public void setIdForm(Form idForm) {
        this.idForm = idForm;
    }

    public Pregunta getIdpregunta() {
        return idpregunta;
    }

    public void setIdpregunta(Pregunta idpregunta) {
        this.idpregunta = idpregunta;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

}
