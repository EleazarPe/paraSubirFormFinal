package org.example.jsonClassConvert;

import org.bson.types.ObjectId;

import java.util.ArrayList;

public class jsonClasificador {
    private String clasificadorId;
    private ArrayList<jsonPregunta> preguntaArrayList;
    public jsonClasificador(ObjectId clasificadorId){
        this.clasificadorId = String.valueOf(clasificadorId);
        this.preguntaArrayList = new ArrayList<>();
    }
    public void addpreguntas(jsonPregunta nueva){
        this.preguntaArrayList.add(nueva);
    }

    public ArrayList<jsonPregunta> getPreguntaArrayList() {
        return preguntaArrayList;
    }

    public void setPreguntaArrayList(ArrayList<jsonPregunta> preguntaArrayList) {
        this.preguntaArrayList = preguntaArrayList;
    }

    public String getClasificadorId() {
        return clasificadorId;
    }

    public void setClasificadorId(ObjectId clasificadorId) {
        this.clasificadorId = String.valueOf(clasificadorId);
    }

}
