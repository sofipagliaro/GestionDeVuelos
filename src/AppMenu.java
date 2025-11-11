import clases.*;
import enums.Puesto;
import gestores.GestionAerolinea;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class AppMenu {

    private static final Scanner sc = new Scanner(System.in);
    private static GestionAerolinea gestor = new GestionAerolinea();

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("  BIENVENIDO AL SISTEMA DE VUELOS  ");
        System.out.println("====================================");

        System.out.print("Ingrese usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Ingrese contraseña: ");
        String password = sc.nextLine();

        Persona persona = gestor.autenticarUsuario(usuario, password);

        // Identificación del tipo de persona
        if (persona instanceof Pasajero) {
            System.out.println("Bienvenido pasajero " + persona.getNombre());
            menuPasajero((Pasajero) persona);
        } else if (persona instanceof Administrador) {
            System.out.println("Bienvenido administrador " + persona.getNombre());
            menuAdministrador((Administrador) persona);
        } else if (persona instanceof Empleado) {
            System.out.println("Bienvenido empleado " + persona.getNombre());
            menuEmpleado((Empleado) persona);
        }


    }

    public static void menuPasajero(Pasajero pasajero) {
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
    public static void menuEmpleado(Empleado empleado) {
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
    public static void menuAdministrador(Administrador admin) {
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
                    try {
                        sc.nextLine();
                        System.out.println("Ingrese la clave del vuelo");
                        String idVuelo = sc.nextLine();

                        System.out.print("Ingrese fecha y hora de partida (YYYY-MM-DDTHH:MM, ej: 2026-06-15T14:30): ");
                        String fechaHoraStr = sc.nextLine();
                        LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr);

                        System.out.print("Ingrese duración del vuelo en minutos: ");
                        int duracion = sc.nextInt();

                        System.out.print("Ingrese precio base del pasaje: ");
                        double precio = sc.nextDouble();
                        sc.nextLine();

                        System.out.println("Ingrese el id del Avion al que quiere asignarle el vuelo. Estos son los ID disponibles");
                        Set<String> idsAviones = gestor.leerTodosLosIdsAvion();
                        for (String id : idsAviones) {
                            System.out.println("ID: " + id); // Muestra el ID al usuario
                        }
                        String idAvion = sc.nextLine();

                        System.out.println("Ingrese el id del Aeropuerto de origen. Estos son los ID disponibles");
                        Set<String> idsAeropuertosOrigen = gestor.leerTodosLosIdsAeropuerto();
                        for (String id : idsAeropuertosOrigen) {
                            System.out.println("ID: " + id); // Muestra el ID al usuario
                        }
                        String idOrigen = sc.nextLine();

                        System.out.println("Ingrese el id del Aeropuerto de destino. Estos son los ID disponibles");
                        Set<String> idsAeropuertosDestino = gestor.leerTodosLosIdsAeropuerto();
                        for (String id : idsAeropuertosDestino) {
                            System.out.println("ID: " + id); // Muestra el ID al usuario
                        }
                        String idDestino = sc.nextLine();

                        Vuelo nuevoVuelo = new Vuelo(idVuelo, null, null, fechaHora, duracion, null, new HashMap<>(), precio);

                        System.out.println("→ Creando vuelo...");
                        gestor.crearVuelo(idVuelo, idAvion, idOrigen, idDestino, nuevoVuelo);
                    } catch (java.time.format.DateTimeParseException e) {
                        System.out.println("ERROR: Formato de fecha/hora incorrecto. Use YYYY-MM-DDTHH:MM.");
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("ERROR: Ingrese solo números para duración y precio.");
                        sc.nextLine(); // Limpiar el buffer del Scanner
                    } catch (excepciones.IdDuplicadoException e) {
                        System.out.println("ERROR: Ya existe un vuelo con esa clave.");
                    } catch (excepciones.IdNoExistenteException e) {
                        System.out.println("ERROR: Uno de los IDs ingresados (Avión, Origen o Destino) no existe.");
                    } catch (excepciones.ValorInvalidoException e) {
                        System.out.println("ERROR DE VALIDACIÓN: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("ERROR INESPERADO: " + e.getMessage());
                    }

                    break;
                case 2:
                    sc.nextLine();
                    String idVueloAEliminar = "";

                    try {
                        System.out.println("IDs de Vuelos disponibles:");
                        Set<String> idsVuelos = gestor.leerTodosLosIdsVuelo();

                        if (idsVuelos.isEmpty()) {
                            System.out.println("No hay vuelos registrados para eliminar.");
                            break;
                        }
                        for (String id : idsVuelos) {
                            System.out.println("ID Vuelo: " + id);
                        }

                        System.out.print("Ingrese el ID del vuelo a eliminar: ");
                        idVueloAEliminar = sc.nextLine();

                        System.out.print("→ Intentando eliminar vuelo " + idVueloAEliminar + "... ");

                        gestor.eliminarVuelo(idVueloAEliminar);

                        System.out.println("Operación completada.");

                    } catch (excepciones.IdNoExistenteException e) {
                        System.out.println("ERROR: El ID de vuelo ingresado no existe o no fue encontrado en el sistema.");
                    } catch (excepciones.VueloConReservasException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("ERROR INESPERADO: " + e.getMessage());
                    }
                    break;


                case 3:
                    sc.nextLine();
                    try {

                        sc.nextLine();
                        System.out.println("Ingrese el dni del empleado");
                        String dni = sc.nextLine();

                        System.out.print("Ingrese nombre del empleado: ");
                        String nombre = sc.nextLine();

                        System.out.println("Ingrese el apellido del empleado");
                        String apellido = sc.nextLine();

                        System.out.print("Ingrese direccion del empleado: ");
                        String direccion = sc.nextLine();

                        System.out.println("Ingrese el telefono del empleado");
                        long telefono = sc.nextLong();
                        sc.nextLine();

                        System.out.print("Ingrese email del empleado: ");
                        String email = sc.nextLine();

                        System.out.print("Ingrese fecha y hora de nacimiento (YYYY-MM-DD, ej: 1995-03-01): ");
                        String fechaNacimiento = sc.nextLine();
                        LocalDate fechaNac = LocalDate.parse(fechaNacimiento);

                        System.out.println("Ingrese el usuario del empleado");
                        String usuario = sc.nextLine();

                        System.out.print("Ingrese password del empleado: ");
                        String password = sc.nextLine();

                        System.out.println("Ingrese la fecha de ingreso del empleado");
                        String fechaIngreso = sc.nextLine();
                        LocalDate fechaIngr = LocalDate.parse(fechaIngreso);

                        Empleado empleado = new Empleado(dni, nombre, apellido, direccion, telefono, email, fechaNac, usuario, password, Puesto.ASISTENTE_VENTAS, fechaIngr);

                        System.out.println("→ Creando empleado...");
                        gestor.crearPersona(dni, empleado);

                        System.out.println("Empleado creado con éxito.");

                    } catch (java.time.format.DateTimeParseException e) {
                        System.out.println("ERROR: Formato de fecha incorrecto. Use YYYY-MM-DD.");
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("ERROR: Ingrese solo números válidos (sin letras) para el teléfono.");
                        sc.nextLine();
                    } catch (excepciones.IdDuplicadoException e) {
                        System.out.println("ERROR: Ya existe una persona con ese DNI.");
                    } catch (Exception e) {
                        System.out.println("ERROR INESPERADO: " + e.getMessage());
                    }
                    break;
                case 4:
                    sc.nextLine();
                    try {
                        System.out.println("\n--- ELIMINAR EMPLEADO ---");

                        System.out.print("Ingrese el DNI de la persona/empleado a eliminar: ");
                        String dniAEliminar = sc.nextLine();

                        System.out.println("→ Intentando eliminar empleado con DNI: " + dniAEliminar + "...");

                        Persona personaAEliminar = gestor.obtenerPersonaPorId(dniAEliminar);

                        if (personaAEliminar instanceof Empleado || personaAEliminar instanceof Administrador) {

                            System.out.println("→ Intentando eliminar empleado con DNI: " + dniAEliminar + "...");

                            gestor.eliminarPersona(dniAEliminar);

                            System.out.println("El empleado con DNI " + dniAEliminar + " ha sido eliminado con éxito.");

                        } else {
                            System.out.println("ERROR: La persona con DNI " + dniAEliminar + " es un Pasajero. Solo se pueden eliminar Empleados o Administradores desde este menú.");
                        }
                    } catch (excepciones.IdNoExistenteException e) {
                        System.out.println("ERROR: No se encontró ninguna persona/empleado con el DNI ingresado.");
                    } catch (Exception e) {
                        System.out.println("ERROR INESPERADO: " + e.getMessage());
                    }
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