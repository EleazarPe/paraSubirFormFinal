package org.example.services;

import com.mongodb.client.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.MorphiaCursor;
import dev.morphia.query.Query;
import dev.morphia.query.filters.Filters;
import dev.morphia.query.updates.UpdateOperators;
import org.bson.types.ObjectId;
import org.example.encapsulaciones.Form;
import org.example.encapsulaciones.Usuario;
import org.example.encapsulaciones.temp;
import org.example.util.MongoDBConnection;

import java.util.ArrayList;
import java.util.List;

import static dev.morphia.query.filters.Filters.eq;

@SuppressWarnings("removal")
public class GestionDB<T> {
    private Class<T> claseEntidad;
    private MongoClient cliente = MongoDBConnection.connect();
    public GestionDB(Class<T> claseEntidad) {

        this.claseEntidad = claseEntidad;

    }

    public void crear(T entidad){

        Datastore datastore = Morphia.createDatastore(cliente);
        try {
            datastore.save(entidad);
        }catch (Exception  e){
            e.printStackTrace();
        }

    }
    public Query<T> find(){
        Datastore datastore = Morphia.createDatastore(cliente);
        Query<T> query = datastore.find(claseEntidad);
        return query;
    }
    public T findByID(String id){
        Datastore datastore = Morphia.createDatastore(cliente);
        Query<T> query = datastore.find(claseEntidad).filter("_id", new ObjectId(id));
        return  query.first();
    }

    public T Validacion(String usuario, String password){
        Datastore datastore = Morphia.createDatastore(cliente);
        Query<T> query = datastore.find(claseEntidad).filter("usuario", usuario).filter("password",password);
        return  query.first();
    }

    public List<Form> FormsByUsuario(ObjectId id){
        Datastore datastore = Morphia.createDatastore(cliente);
        Query<Form> query = datastore.find(Form.class);
        List<Form> nueva = new ArrayList<>();
        for (Form t: query
             ) {
            for (Usuario u: t.getUsuarios()
                 ) {
                if(u.getId().equals(id)){
                    nueva.add(t);
                    break;
                }
            }
        }
        return nueva;
    }


    public void updateDataByID(String id, temp newData) {
        Datastore datastore = Morphia.createDatastore(cliente);
        datastore.find(temp.class)
                .filter("_id", new ObjectId(id))
                .update(UpdateOperators.set("nombre", newData.getNombre()),
                        UpdateOperators.set("apellido", newData.getApellido()))
                .execute();
    }

    public void deleteById(String id){
        Datastore datastore = Morphia.createDatastore(cliente);
        datastore.find(claseEntidad).filter("_id", new ObjectId(id)).delete();

    }

}
