package org.example.controladores;

import io.javalin.Javalin;
import org.example.encapsulaciones.Usuario;
import org.example.services.UsuarioServices;
import org.example.util.BaseControlador;

public class UserAccess extends BaseControlador {
    public UserAccess(Javalin app) {super(app);}

    @Override
    public void aplicarRutas() {
        app.post("/registerprocesor", ctx -> {
            UsuarioServices.getInstance().crear(new Usuario(ctx.formParam("user"),ctx.formParam("fn"),ctx.formParam("ln"),ctx.formParam("password"), false));
            ctx.redirect("/login");
        });
        app.get("/register", ctx->{ctx.redirect("register.html");});

    }

}
