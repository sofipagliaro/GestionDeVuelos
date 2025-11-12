package clases;
import clases.Administrador;
import clases.Empleado;
import clases.Pasajero;
import clases.Persona;
import java.util.HashMap;
import java.util.Scanner;

public class GestionUsuario {

    public static Persona login(String usuario, String password, HashMap<String, Persona> mapeoPersonas) {
        if (mapeoPersonas.containsKey(usuario)) {
            Persona persona = mapeoPersonas.get(usuario);
            if (persona.getPassword().equals(password)) {
                return persona; // Login correcto
            }
        }
        return null; // Usuario no encontrado o contraseña incorrecta
    }

    /// Cargo los menu:

}
