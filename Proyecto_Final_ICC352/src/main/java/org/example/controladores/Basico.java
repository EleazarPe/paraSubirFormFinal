package org.example.controladores;

import io.javalin.Javalin;
import j2html.tags.ContainerTag;
import org.example.encapsulaciones.*;
import org.example.services.*;
import org.example.util.BaseControlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;
import static j2html.TagCreator.*;

public class Basico extends BaseControlador {
    public Basico(Javalin app) {super(app);}

    @Override
    public void aplicarRutas() {
        app.routes( ()->{
            path("*", ()->{
                before(ctx -> {
                    /*if(!ctx.path().startsWith("/1") && !ctx.path().startsWith("/notfound/index.html") &&!ctx.path().startsWith("/notfound/style.css")&& !ctx.path().startsWith("/scriptsMain.js") ){
                        ctx.redirect("/notfound/index.html");
                    }*/
                });

            });

        });
        app.routes( ()->{
            path("/", ()->{
                before(ctx -> {

                });
                get("/login", ctx -> {
                    ctx.redirect("/login.html");
                });
                get("/index2", ctx -> {
                    ctx.redirect("/index2.html");
                });
                get("/", ctx -> {
                    System.out.println("--------------"+ctx.queryParam("data") );
                    if(ctx.queryParam("data") == null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("mostrarRedirect", true);
                        modelo.put("location", "/");
                        ctx.render("/publico/carga/carga.html", modelo);
                    }else{
                        if(Criptografia.desencriptado(ctx.queryParam("data"))!=null) {
                            Map<String, Object> modelo = new HashMap<>();
                            modelo.put("hola", ".");
                            ctx.render("/publico/index.html", modelo);
                        }else{
                            ctx.redirect("/login");
                        }
                    }
                });
                get("/index.html", ctx -> {
                    ctx.redirect("/");
                });

                get("/table.html", ctx -> {
                    ctx.redirect("/table");
                });
                get("/survey.html", ctx -> {
                    ctx.redirect("/survey");
                });
                get("/table", ctx -> {
                    System.out.println("--------------"+ctx.queryParam("data") );
                    if(ctx.queryParam("data") == null) {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("mostrarRedirect", true);
                        modelo.put("location", "/table");
                        ctx.render("/publico/carga/carga.html", modelo);
                    }else{
                        if(Criptografia.desencriptado(ctx.queryParam("data"))!=null) {
                            Map<String, Object> modelo = new HashMap<>();
                            modelo.put("hola", ".");
                            ctx.render("/publico/table.html", modelo);
                        }else{
                            ctx.redirect("/login");
                        }
                    }
                });


                get("/1", ctx->{
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("text", "hola mundo");
                    ctx.render("/publico/index.html", modelo);
                });
                post("/filtrar", ctx->{
                    int pregunta = Integer.parseInt(ctx.formParam("Pregunta"));
                    int seleccion = Integer.parseInt(ctx.formParam("Seleccion"));
                    int escala = Integer.parseInt(ctx.formParam("Escala"));
                    List<String> datausertemp = ctx.formParams("userSelect");
                    System.out.println("Lista:  "+ datausertemp);
                    System.out.println("p:"+pregunta+" s:"+seleccion+" e:"+escala);
                    Form tempform = new Form();
                    ArrayList<Pregunta> preguntas = new ArrayList<>();
                    ArrayList<Seleccion> select = new ArrayList<>();
                    ArrayList<Escala> escale = new ArrayList<>();
                    int tempID =0;
                    for (int i =0; i<pregunta; i++){
                        System.out.println("Pregunta P"+i+" "+ ctx.formParam("P"+i));
                        preguntas.add(new Pregunta(ctx.formParam("P"+i)));
                        tempID++;

                    }

                    for(int i =0; i<seleccion; i++){
                        ArrayList<Respuesta> tempRespuesta = new ArrayList<>();
                        System.out.println("pregunta: "+ctx.formParam("S"+i));
                        int cantEscala = Integer.parseInt(ctx.formParam("S"+i+"C"));
                        for (int j=0; j< cantEscala; j++ ){
                            tempRespuesta.add(new Respuesta(ctx.formParam(i+"S"+j)));
                            System.out.println(ctx.formParam(i+"S"+j));
                        }
                        Seleccion tempSelect =new Seleccion(ctx.formParam("S"+i));
                        for (Respuesta r: tempRespuesta
                        ) {
                            RespuestaServices.getInstance().crear(r);
                        }
                        tempSelect.setRespuestas(tempRespuesta);
                        SeleccionServices.getInstance().crear(tempSelect);
                        select.add(tempSelect);
                        tempID++;
                    }

                    for (int i =0; i<escala; i++){
                        System.out.println("Pregunta E"+i+" "+ ctx.formParam("E"+i));
                        System.out.println("Minimo: "+ctx.formParam("minimo_"+i)+" Maximo: "+ctx.formParam("maximo_"+i)+" Aumento:"+ctx.formParam("pasos_"+i));
                        escale.add(new Escala(ctx.formParam("E"+i), Integer.parseInt(ctx.formParam("minimo_"+i)), Integer.parseInt(ctx.formParam("maximo_"+i)), Integer.parseInt(ctx.formParam("pasos_"+i))));
                        tempID++;
                    }
                    for (Pregunta p: preguntas
                    ) {
                        PregunatServices.getInstance().crear(p);
                    }
                    for (Seleccion s: select
                    ) {
                        SeleccionServices.getInstance().crear(s);
                    }
                    for (Escala e: escale
                    ) {
                        EscalaServices.getInstance().crear(e);
                    }
                    tempform.setMispreguntas(preguntas);
                    tempform.setMisselecciones(select);
                    tempform.setMisescalas(escale);
                    for (String str: datausertemp
                         ) {
                        tempform.insertUsuario(UsuarioServices.getInstance().findByID(str));
                    }
                    //tempform.insertUsuario(UsuarioServices.getInstance().Validacion("cepg0001","123456"));
                    FormServices.getInstance().crear(tempform);
                    ctx.redirect("/survey");
                });

            });

        });



        app.routes( ()->{
            path("/formulario", ()->{
                before(ctx -> {

                });
                get("/{formulario}", ctx->{
                    String num = ctx.pathParamAsClass("formulario", String.class).get();
                    if(FormServices.getInstance().findByID(num) == null){
                        ctx.redirect("/notfound");
                    }else {
                        ctx.html(generateFormWithParts(FormServices.getInstance().findByID(num)));
                    }
                });

            });

        });
        app.routes( ()->{
            path("/procesar", ()->{
                before(ctx -> {

                });
                post("/{data}", ctx->{
                    String num = ctx.pathParamAsClass("data", String.class).get();
                    if(FormServices.getInstance().findByID(num) == null){
                        ctx.redirect("/notfound");
                    }else {
                        DataUser temp = null;
                        for (Pregunta p: FormServices.getInstance().findByID(num).getMispreguntas()
                        ) {

                        }
                        for (Seleccion s: FormServices.getInstance().findByID(num).getMisselecciones()
                        ) {

                        }
                        for (Escala e: FormServices.getInstance().findByID(num).getMisescalas()
                        ) {

                        }
                        //for (Pregunta p: FormServices.getInstance().findByID(num).getMispreguntas()
                        //    ) {
                        // temp = new DataUser( FormServices.getInstance().findByID(num),p,null,ctx.pathParamAsClass(p.getId().toString(), String.class).get());
                        // DataUserServices.getInstance().crear(temp);

                        // }
                    }
                });

            });

        });

        app.routes( ()->{
            path("/result", ()->{
                before(ctx -> {

                });
                get("/{id}", ctx->{
                    String resultado = "";
                    String num = ctx.pathParamAsClass("id", String.class).get();
                    for (DataUser dd: DataUserServices.getInstance().find()
                    ) {
                        if(dd.getIdForm().toString().equals(num)){
                            resultado = resultado.concat(dd.getIdpregunta()+dd.getRespuesta());
                        }
                    }
                    ctx.html(resultado);
                });

            });

        });



        //fin
    }
    public static String generateFormWithParts(Form formulario) {
        ContainerTag botoncolap = button().withClass("collapsible");
        //ContainerTag divColap = div().withClass("content");
        ContainerTag form = form().withId(formulario.getId().toString()).attr("onsubmit","procesarFormulario('"+formulario.getId().toString()+"'); return false;");

        for (Pregunta p: formulario.getMispreguntas()
        ) {
            form.with(
                    div(
                            label(p.getTitulo()).attr("for", p.getId().toString()),
                            input().withType("text").withName(p.getId().toString())
                    ).withClass("form-group")
            );
        }
        //System.out.println(formulario.getMisselecciones());
        for (Seleccion p: formulario.getMisselecciones()
        ) {
            System.out.println("----------------"+p.getTitulo());
            ContainerTag radioButtons = div().withClass("");
            radioButtons.with(label(p.getTitulo()));
            for (Respuesta i: p.getRespuestas()) {
                System.out.println(i.getTitulo());
                radioButtons.with(
                        input()
                                .withType("radio")
                                .withName(p.getId().toString()).withId("S"+p.getId().toString()).withValue(i.getTitulo()),
                        label(i.getTitulo()).attr("for", "S"+p.getId().toString())
                );

            }
            form.with(radioButtons);
        }

        for (Escala p: formulario.getMisescalas()
        ) {


            form.with(
                    div(
                            label(p.getTitulo()).attr("for", p.getId().toString()),
                            input().withType("range").withName(p.getId().toString()).attr("max", Integer.toString(((Escala) p).getMaxima())).attr("min", Integer.toString(((Escala) p).getMinima())).attr("step", Integer.toString(((Escala) p).getPasos()))
                    ).withClass("form-group")
            );
        }


        form.with(input().withType("submit").withValue("Enviar"));

        return div( button().withClass("collapsible").withText(formulario.getId().toString()), div(form).withClass("container").withClass("content")).render();
    }

}



