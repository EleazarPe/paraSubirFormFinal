package org.example.encapsulaciones;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import org.bson.types.ObjectId;

@Entity
public class relacion {
    @Id
    private ObjectId id;
    @Reference
    private temp tempid;
    public relacion(){

    }
    public relacion(temp idrrelacion){
        this.tempid = idrrelacion;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public temp getTempid() {
        return tempid;
    }

    public void setTempid(temp tempid) {
        this.tempid = tempid;
    }
}
