package org.example.controladores;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.javalin.Javalin;
import org.example.encapsulaciones.Criptografia;
import org.example.encapsulaciones.Usuario;
import org.example.services.UsuarioServices;
import org.example.util.BaseControlador;

import java.util.HashMap;
import java.util.Map;

public class Autenticar extends BaseControlador {
    public Autenticar(Javalin app) {super(app);}

    @Override
    public void aplicarRutas() {
        app.post("/autenticar", ctx -> {
            String nombreUsuario = ctx.formParam("username");
            String password = ctx.formParam("password");
            Usuario user = UsuarioServices.getInstance().Validacion(nombreUsuario,password);
            if(user == null){
                ctx.redirect("/login");
            }else{
                Map<String, Object> modelo = new HashMap<>();
                modelo.put("mostrarEtiqueta",true);
                modelo.put("mostrarRedirect",true);
                modelo.put("location","/index.html");
                modelo.put("usertoken", Criptografia.encriptado(user));
                System.out.println("Encriptado:"+Criptografia.encriptado(user));
                //System.out.println(Criptografia.desencriptado("bcOM7cdwVTAzDrX0nQmoGXUnBGsRSz35"));
                ctx.render("/publico/carga/carga.html",modelo);
            }
        });
        app.post("/verify", ctx -> {
            String requestBody = ctx.body(); // Get the JSON data from the request body
            JsonObject jsonDataToSend = JsonParser.parseString(requestBody).getAsJsonObject();
            String dataValue = jsonDataToSend.getAsJsonObject("dataToSend").get("data").getAsString();
            System.out.println("Received data: " +  dataValue);
            Usuario temp = Criptografia.desencriptado(dataValue);
            if (temp != null) {
                ctx.result("true");
            }else{
                ctx.result("false");
            }
        });
    }

}
