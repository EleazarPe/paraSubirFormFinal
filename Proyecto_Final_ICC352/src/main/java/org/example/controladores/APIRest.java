package org.example.controladores;

import io.javalin.Javalin;
import org.bson.types.ObjectId;
import org.example.encapsulaciones.Clasificador;
import org.example.encapsulaciones.DataUser;
import org.example.jsonClassConvert.jsonClasificador;
import org.example.jsonClassConvert.jsonForm;
import org.example.jsonClassConvert.jsonPregunta;
import org.example.services.ClasificadorServices;
import org.example.util.BaseControlador;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static io.javalin.apibuilder.ApiBuilder.*;

public class APIRest extends BaseControlador {
    public APIRest(Javalin app) {
        super(app);
    }

    @Override
    public void aplicarRutas() {
        app.routes(()->{
           path("/apirest",()->{
               get("/{id}", ctx->{
                   System.out.println("Consulta");
                   String idtemp= ctx.pathParamAsClass("id", String.class).get();
                   System.out.println("ruta---->  "+idtemp);
                   ArrayList<Clasificador> temp = new ArrayList<>();
                   //jsonForm nuevojsonform  = new jsonForm(null, null);
                   for (Clasificador c: ClasificadorServices.getInstance().find()
                        ) {
                       if(c.getUsuario().getId().toString().equals(idtemp)){
                            temp.add(c);
                       }
                   }


                   //jsonClasificador classi = new jsonClasificador();
                   ArrayList<jsonClasificador>lista = new ArrayList<>();
                   for (Clasificador c: temp
                        ) {
                       jsonClasificador classi = new jsonClasificador(c.getId());
                       for (DataUser dt: c.getDatos()
                            ) {
                           //nuevojsonform.setIdform(dt.getIdForm().getId());
                           //nuevojsonform.setClasificadores();
                           System.out.println(dt.getIdusuario().getNombre());
                           if(dt.getIdpregunta() != null){
                               classi.addpreguntas(new jsonPregunta( dt.getIdpregunta().getId(), dt.getIdpregunta().getTitulo(),dt.getRespuesta()));
                           }else if(dt.getIdseleccion() != null){
                               classi.addpreguntas(new jsonPregunta( dt.getIdseleccion().getId(), dt.getIdseleccion().getTitulo(),dt.getRespuesta()));
                           }else{
                               classi.addpreguntas(new jsonPregunta( dt.getIdescala().getId(), dt.getIdescala().getTitulo(),dt.getRespuesta()));
                           }

                          // new jsonPregunta( dt.getIdpregunta().getId(), dt.getIdpregunta().getTitulo(),dt.getRespuesta());
                          // dt.getIdForm();
                          // c.getId();
                           //dt.getIdpregunta().getId();
                          // dt.getIdpregunta().getTitulo();
                          // dt.getRespuesta();
                           //JSONObject jsonC = new JSONObject(dt);
                           //jsonArray.put(jsonC);
                       }
                       lista.add(classi);
                   }
                   JSONArray jsonArray = new JSONArray();
                   for (jsonClasificador cl: lista
                        ) {
                       JSONObject jsonC = new JSONObject(cl);
                       jsonArray.put(jsonC);
                   }

                   ctx.json(jsonArray.toString());
               });
           });
        });
    }
}
