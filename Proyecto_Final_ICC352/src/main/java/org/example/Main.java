package org.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;
import org.example.controladores.*;
import org.example.encapsulaciones.Usuario;
import org.example.services.UsuarioServices;
import org.example.util.EnvServices;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        var app = Javalin.create(config ->{
            //configurando los documentos estaticos.
            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
                staticFileConfig.location = Location.CLASSPATH;
                staticFileConfig.precompress=false;
                staticFileConfig.aliasCheck=null;
            });}).start(7070);
        new Basico(app).aplicarRutas();
        new Autenticar(app).aplicarRutas();
        new Encuesta(app).aplicarRutas();
        new UserAccess(app).aplicarRutas();
        new APIRest(app).aplicarRutas();
        //UsuarioServices.getInstance().crear(new Usuario("cepg0001","123456","Carlos","Peterson",true));
        app.post("/log-request", Main::logRequest);

        app.error(404, ctx -> {
            System.out.println("No se alcanzo la ruta: "+ctx.path());
            ctx.redirect("/notfound/index.html");
        });
        System.out.println(EnvServices.getInstance().result("CLAVE"));

        app.post("/conect", ctx -> {
            ctx.status(200);
        });
        /*app.post("/log-request", ctx->{
            ctx.json(new HashMap<String, Object>() {{
                put("message", "Formulario subido exitosamente");
            }});
        });*/

    }

    /*private static void logRequest(Context ctx) {
        System.out.println("Se hizo la consulta");

        String requestBody = ctx.body(); // Get the JSON data from the request body

        JsonObject jsonData = JsonParser.parseString(requestBody).getAsJsonObject();

        System.out.println("Received data: " +  jsonData.get("data").getAsString());

        // Rest of your logging code for headers and form parameters
    }*/
    private static void logRequest(Context ctx) {
        StringBuilder headersInfo = new StringBuilder("Headers:\n");
        ctx.headerMap().forEach((header, value) -> headersInfo.append(header).append(": ").append(value).append("\n"));
        StringBuilder formParamsInfo = new StringBuilder("Form Params:\n");
        ctx.formParamMap().forEach((param, value) -> formParamsInfo.append(param).append(": ").append(value).append("\n"));
        String requestInfo = headersInfo.toString() + "\n" + formParamsInfo.toString();
        ctx.result(requestInfo);
    }

}