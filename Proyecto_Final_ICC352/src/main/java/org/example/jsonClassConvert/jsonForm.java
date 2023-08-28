package org.example.jsonClassConvert;

import org.bson.types.ObjectId;

import java.util.ArrayList;

public class jsonForm {
    private ObjectId idform;
    private ArrayList<jsonClasificador> clasificadores;
    public jsonForm(ObjectId idform, ArrayList<jsonClasificador> clasificadores) {
        this.idform = idform;
        this.clasificadores = clasificadores;
    }
    public ObjectId getIdform() {
        return idform;
    }

    public void setIdform(ObjectId idform) {
        this.idform = idform;
    }

    public ArrayList<jsonClasificador> getClasificadores() {
        return clasificadores;
    }

    public void setClasificadores(ArrayList<jsonClasificador> clasificadores) {
        this.clasificadores = clasificadores;
    }
}
