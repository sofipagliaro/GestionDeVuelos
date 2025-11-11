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
    public static void menuPasajero(Scanner sc, Pasajero pasajero) {
        int opcion;
        do {
            System.out.println("\n===== MENÚ PASAJERO =====");
            System.out.println("1. Buscar vuelos disponibles");
            System.out.println("2. Ver mis reservas");
            System.out.println("3. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("→ Buscar vuelos disponibles...");
                    // Llamar a pasajero.buscarVuelosDisponibles();
                    break;
                case 2:
                    System.out.println("→ Mostrando reservas...");
                    // Mostrar reservas del pasajero
                    break;
                case 3:
                    System.out.println("→ Cerrando sesión...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 3);
    }

    // ========================
    // MENU EMPLEADO
    // ========================
    public static void menuEmpleado(Scanner sc, Empleado empleado) {
        int opcion;
        do {
            System.out.println("\n===== MENÚ EMPLEADO =====");
            System.out.println("1. Crear reserva");
            System.out.println("2. Modificar reserva");
            System.out.println("3. Eliminar reserva");
            System.out.println("4. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("→ Creando reserva...");
                    // empleado.crearReserva();
                    break;
                case 2:
                    System.out.println("→ Modificando reserva...");
                    // empleado.modificarReserva();
                    break;
                case 3:
                    System.out.println("→ Eliminando reserva...");
                    // empleado.eliminarReserva();
                    break;
                case 4:
                    System.out.println("→ Cerrando sesión...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 4);
    }

    // ========================
    // MENU ADMINISTRADOR
    // ========================
    public static void menuAdministrador(Scanner sc, Administrador admin) {
        int opcion;
        do {
            System.out.println("\n===== MENÚ ADMINISTRADOR =====");
            System.out.println("1. Crear vuelo");
            System.out.println("2. Eliminar vuelo");
            System.out.println("3. Crear empleado");
            System.out.println("4. Eliminar empleado");
            System.out.println("5. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("→ Creando vuelo...");
                    // admin.crearVuelo();
                    break;
                case 2:
                    System.out.println("→ Eliminando vuelo...");
                    // admin.eliminarVuelo();
                    break;
                case 3:
                    System.out.println("→ Creando empleado...");
                    // admin.crearEmpleado();
                    break;
                case 4:
                    System.out.println("→ Eliminando empleado...");
                    // admin.eliminarEmpleado();
                    break;
                case 5:
                    System.out.println("→ Cerrando sesión...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

}
