package clases;
import clases.Administrador;
import clases.Empleado;
import clases.Pasajero;
import clases.Persona;
import java.util.HashMap;
import java.util.Scanner;

public class GestionUsuario {

    public static Persona login(String usuario, String password, HashMap<String, Persona> mapaPersonas) {
        if (mapaPersonas.containsKey(usuario)) {
            Persona persona = mapaPersonas.get(usuario);
            if (persona.getPassword().equals(password)) {
                return persona; // Login correcto
            }
        }
        return null; // Usuario no encontrado o contraseña incorrecta
    }

    /// Cargo los menu:

}
