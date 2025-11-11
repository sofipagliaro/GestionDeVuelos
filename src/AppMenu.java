import clases.Administrador;
import clases.Empleado;
import clases.Pasajero;
import clases.Persona;

import java.util.Scanner;

import static clases.GestionUsuario.*;
//import static gestion.GestionUsuario.login;

public class AppMenu {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);


        System.out.println("====================================");
        System.out.println("✈️  BIENVENIDO AL SISTEMA DE VUELOS ✈️");
        System.out.println("====================================");
        System.out.print("Ingrese usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Ingrese contraseña: ");
        String password = sc2.nextLine();

// Autenticación
       Persona persona = login(usuario, password, mapaPersonas);
        if (persona == null) {
            System.out.println("Usuario o contraseña incorrectos.");
            return;
        }

// Identificación del tipo de persona
        if (persona instanceof Pasajero) {
            System.out.println("Bienvenido pasajero " + persona.getNombre());
            menuPasajero(sc, (Pasajero) persona);

        } else if (persona instanceof Empleado) {
            System.out.println("Bienvenido empleado " + persona.getNombre());
            menuEmpleado(sc, (Empleado) persona);

        } else if (persona instanceof Administrador) {
            System.out.println("Bienvenido administrador " + persona.getNombre());
            menuAdministrador(sc, (Administrador) persona);
        }
    }
}