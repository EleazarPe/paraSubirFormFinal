package org.example.encapsulaciones;

import org.example.services.UsuarioServices;
import org.example.util.EnvServices;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Criptografia {
    private static final String clave = EnvServices.getInstance().result("CLAVE");
    private static StandardPBEStringEncryptor encryptor;

    static {
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(clave);
    }

    public static String encriptado(Usuario us) {
        String encrips = us.getUsuario() + ";" + us.getPassword();
        return encryptor.encrypt(encrips);
    }

    public static Usuario desencriptado(String txt) {
        String textoDesencriptado = encryptor.decrypt(txt);
        if (textoDesencriptado == null) {
            return null;
        }
        String[] partes = textoDesencriptado.split(";", 2);
        if (partes.length == 2) {
            String nombre = partes[0];
            String password = partes[1];
            System.out.println("Nombre: "+nombre+" password: "+password);
            return UsuarioServices.getInstance().Validacion(nombre, password);
        } else {
            return null;
        }
    }
}
