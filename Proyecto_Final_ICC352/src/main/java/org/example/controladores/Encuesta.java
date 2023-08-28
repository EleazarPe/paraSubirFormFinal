package org.example.controladores;

import io.javalin.Javalin;
import org.example.encapsulaciones.*;
import org.example.services.*;
import org.example.util.BaseControlador;
import org.json.JSONException;
import org.json.JSONObject;

import javax.json.JsonObject;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.controladores.Basico.generateFormWithParts;

public class Encuesta extends BaseControlador {
    public Encuesta(Javalin app) {
        super(app);
    }

    @Override
    public void aplicarRutas() {

        app.get("/survey", ctx->{
            System.out.println("--------------"+ctx.queryParam("data") );
            if(ctx.queryParam("data") == null) {
                Map<String, Object> modelo = new HashMap<>();
                modelo.put("mostrarRedirect", true);
                modelo.put("location", "/survey");
                ctx.render("/publico/carga/carga.html", modelo);
            }else{
                if(Criptografia.desencriptado(ctx.queryParam("data"))!=null) {
                    Map<String, Object> modelo = new HashMap<>();
                    List<String> listas = new ArrayList<>();
                    for (Form f: FormServices.getInstance().FormsByUsuario(Criptografia.desencriptado(ctx.queryParam("data")).getId())
                         ) {
                        System.out.println("-------------------->: "+f.getId());
                        listas.add(generateFormWithParts(f));

                    }
                    System.out.println(listas.size());
                    modelo.put("forms",listas);
                    ctx.render("/publico/survey.html", modelo);
                }else{
                    ctx.redirect("/login");
                }
            }
        });
        app.post("/subir-formulario", ctx -> {
            String jsonString = ctx.body();
            JSONObject json = new JSONObject(jsonString);
            Usuario user = Criptografia.desencriptado(ctx.header("data"));
            System.out.println(json);
            JSONObject datas = json.getJSONObject("data");
            Form formulario = FormServices.getInstance().findByID(json.getString("formulario"));
            ArrayList<DataUser> data =  new ArrayList<>();
            if( formulario!= null){
                for (Pregunta p: formulario.getMispreguntas()
                     ) {
                    if(datas.getString(p.getId().toString()) != null){
                        System.out.println(datas.getString(p.getId().toString()));
                       Pregunta preg = PregunatServices.getInstance().findByID(p.getId().toString());
                        data.add(new DataUser(formulario,preg, null, null,user,datas.getString(p.getId().toString())));
                    }
                }
                for (Seleccion s: formulario.getMisselecciones()
                ) {
                    if(datas.get(s.getId().toString()) != null){
                        Seleccion sel = SeleccionServices.getInstance().findByID(s.getId().toString());
                        data.add(new DataUser(formulario,null, sel, null,user,datas.getString(s.getId().toString())));
                    }
                }
                for (Escala e: formulario.getMisescalas()
                ) {
                    if(datas.get(e.getId().toString()) != null){
                        Escala es = EscalaServices.getInstance().findByID(e.getId().toString());
                        data.add(new DataUser(formulario,null, null, es,user,datas.getString(e.getId().toString())));
                    }
                }
            }
            for (DataUser dt: data
                 ) {
                DataUserServices.getInstance().crear(dt);
            }
            System.out.println("ID del usuario propietario: "+user.getId());
            ClasificadorServices.getInstance().crear(new Clasificador(data,user));

            ctx.status(200);
        });
    }
}
