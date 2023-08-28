package org.example.jsonClassConvert;

import org.bson.types.ObjectId;

public class jsonPregunta {

    private String idPregunta;

    private String pregunta;
    private String respuesta;
    public  jsonPregunta( ObjectId idPregunta,String pregunta, String respuesta){

        this.idPregunta = String.valueOf(idPregunta);
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public String getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(ObjectId idPregunta) {
        this.idPregunta = String.valueOf(idPregunta);
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
