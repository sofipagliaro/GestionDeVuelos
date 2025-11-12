import clases.*;
import enums.MetodoDePago;
import enums.Puesto;
import enums.TipoEquipaje;
import gestores.GestionAerolinea;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
            System.out.println("\n===== ¡TE DAMOS LA BIENVENIDA PASAJERO/A! =====");
            System.out.println("1.Buscar vuelos por origen y destino");
            /*System.out.println("2. Ver mis reservas");*/
            System.out.println("2. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    sc.nextLine();
                    System.out.println("Ingresa una ciudad de origen");
                    String origen = sc.nextLine();

                    System.out.println("Ingresa una ciudad de destino");
                    String destino = sc.nextLine();

                    List<Vuelo> vuelosEncontrados = gestor.obtenerVuelosPorCiudadesOrigenDestino(origen, destino);

                    if (vuelosEncontrados.isEmpty()) {
                        System.out.println("No se encontraron vuelos");
                    } else {
                        for (Vuelo vuelo : vuelosEncontrados) {
                            System.out.println("  ID Vuelo: " + vuelo.getIdVuelo());
                            System.out.println("  Origen: " + vuelo.getOrigen());
                            System.out.println("  Destino: " + vuelo.getDestino());
                            System.out.println("  Fecha/Hora: " + vuelo.getFechaHora());
                            System.out.println("  Precio Base: $" + vuelo.getPrecio());
                        }
                    }

                    break;
                case 2:
                    System.out.println("→ Cerrando sesión...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 2);
    }

    // ========================
// MENU EMPLEADO
// ========================
    public static void menuEmpleado(Empleado empleado) {
        int opcion;
        do {
            System.out.println("\n===== COMO EMPLEADO DE LA AEROLINIA, ¿QUE DESEAS HACER? =====");
            System.out.println("1. Crear reserva");
            /*System.out.println("2. Modificar reserva");
            System.out.println("3. Eliminar reserva");*/
            System.out.println("4. Crear pasajero");
            System.out.println("5. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    sc.nextLine();
                    try {
                        Pasajero pasajeroPrincipal;
                        String dniPasajero;

                        System.out.print("Ingrese el DNI del pasajero: ");
                        dniPasajero = sc.nextLine();

                        Persona personaExistente = gestor.obtenerPersonaPorId(dniPasajero);

                        pasajeroPrincipal = (Pasajero) personaExistente;
                        System.out.println("Pasajero " + pasajeroPrincipal.getNombre() + " " + pasajeroPrincipal.getApellido() + " encontrado.");

                        System.out.println("\nIDs de Vuelos disponibles:");

                        Set<String> idsVuelos = gestor.leerTodosLosIdsVuelo();
                        for (String id : idsVuelos) {
                            System.out.println("ID Vuelo: " + id);
                        }

                        System.out.print("Ingrese el ID del vuelo a reservar: ");
                        String idVuelo = sc.nextLine();
                        Vuelo vuelo = gestor.obtenerVueloPorId(idVuelo);

                        if (vuelo == null) {
                            throw new excepciones.IdNoExistenteException("El ID de vuelo no existe.");
                        }

                        LocalDateTime ahora = LocalDateTime.now();
                        LocalDateTime fechaVuelo = vuelo.getFechaHora();

                        long horasHastaVuelo = ChronoUnit.HOURS.between(ahora, fechaVuelo);

                        if (horasHastaVuelo < 6) {
                            throw new excepciones.ReservaNoPermitidaException("La reserva debe crearse con al menos 6 horas de antelación. Faltan solo " + horasHastaVuelo + " horas.");
                        }

                        int totalAsientos = vuelo.getAvion().getMapaAsientos().size();
                        HashMap<Integer, Boolean> mapaReservados = vuelo.getAsientosReservados();

                        int asientosOcupados = 0;
                        for (Boolean estaReservado : mapaReservados.values()) {
                            if (estaReservado) {
                                asientosOcupados++;
                            }
                        }

                        System.out.print("Ingrese la cantidad total de pasajes a reservar: ");
                        int cantidadPasajes = sc.nextInt();
                        sc.nextLine();

                        if (cantidadPasajes > 4) {
                            throw new excepciones.ValorInvalidoException("El número máximo de pasajes permitidos por reserva es 4.");
                        }

                        if (cantidadPasajes < 1) {
                            throw new excepciones.ValorInvalidoException("La cantidad de pasajes debe ser mayor que cero.");
                        }

                        int asientosDisponibles = totalAsientos - asientosOcupados;

                        if (cantidadPasajes > asientosDisponibles) {
                            throw new excepciones.ValorInvalidoException("La cantidad de pasajes solicitada (" + cantidadPasajes + ") supera los asientos disponibles (" + asientosDisponibles + ") en el vuelo. Modifique la cantidad.");
                        }

                        if (totalAsientos == asientosOcupados) {
                            throw new excepciones.AsientoNoDisponibleException("El avión está completamente lleno. No se puede crear la reserva.");
                        }

                        System.out.println("\nSeleccione Método de Pago:");
                        System.out.println("1. EFECTIVO | 2. DEBITO | 3. CREDITO");
                        System.out.print("Ingrese el número de la opción: ");
                        int opcionPago = sc.nextInt();
                        sc.nextLine();

                        MetodoDePago metodoDePago = switch (opcionPago) {
                            case 1 -> MetodoDePago.EFECTIVO;
                            case 2 -> MetodoDePago.DEBITO;
                            case 3 -> MetodoDePago.CREDITO;
                            default -> throw new excepciones.ValorInvalidoException("Opción de pago inválida.");
                        };

                        List<DetallePasajero> detalles = new ArrayList<>();

                        List<Integer> idsAsientosLibres = gestor.obtenerIdsAsientosDisponibles(idVuelo);
                        System.out.println("\nAsientos disponibles: " + idsAsientosLibres);

                        for (int i = 0; i < cantidadPasajes; i++) {
                            System.out.println("\n--- Detalle Pasaje N° " + (i + 1) + " ---");

                            Asiento asientoElegido = null;
                            boolean asientoValido = false;

                            while (!asientoValido) {
                                System.out.print("Ingrese el ID del asiento a asignar para este pasaje: ");
                                int idAsiento = sc.nextInt();
                                sc.nextLine();

                                try {
                                    asientoElegido = gestor.obtenerAsientoPorId(idVuelo, idAsiento);
                                    asientoValido = true;
                                } catch (excepciones.IdNoExistenteException |
                                         excepciones.AsientoNoDisponibleException e) {
                                    System.out.println("ERROR: " + e.getMessage() + " Intente de nuevo.");
                                }
                            }

                            List<Equipaje> listaEquipaje = new ArrayList<>();
                            boolean agregandoEquipaje = true;

                            System.out.println("\n--- GESTIÓN DE EQUIPAJE para Pasaje N° " + (i + 1) + " ---");
                            while (agregandoEquipaje) {

                                System.out.println("Seleccione tipo de equipaje a añadir (o 0 para terminar):");
                                System.out.println("1. MOCHILA | 2. MALETA | 3. BODEGA | 4. MASCOTA | 5. EQUIPO_DEPORTIVO | 6. EQUIPO_MUSICAL");
                                System.out.print("Ingrese el número de la opción: ");
                                int opcionEquipaje = sc.nextInt();
                                sc.nextLine();

                                if (opcionEquipaje == 0) {
                                    agregandoEquipaje = false;
                                    continue;
                                }

                                TipoEquipaje tipoSeleccionado = null;
                                int cantidadPiezas = 1;
                                double precioBase = 0.0;

                                switch (opcionEquipaje) {
                                    case 1:
                                        tipoSeleccionado = TipoEquipaje.MOCHILA;
                                        precioBase = 0.0;
                                        break;
                                    case 2:
                                        tipoSeleccionado = TipoEquipaje.MALETA;
                                        precioBase = 25000.0;
                                        break;
                                    case 3:
                                        tipoSeleccionado = TipoEquipaje.BODEGA;
                                        precioBase = 40000.0;
                                        break;
                                    case 4:
                                        tipoSeleccionado = TipoEquipaje.MASCOTA;
                                        precioBase = 75000.0;
                                        break;
                                    case 5:
                                        tipoSeleccionado = TipoEquipaje.EQUIPO_DEPORTIVO;
                                        precioBase = 60000.0;
                                        break;
                                    case 6:
                                        tipoSeleccionado = TipoEquipaje.EQUIPO_MUSICAL;
                                        precioBase = 50000.0;
                                        break;
                                    default:
                                        System.out.println("Opción de equipaje inválida. Intente de nuevo.");
                                        continue;
                                }


                                if (tipoSeleccionado == TipoEquipaje.BODEGA || tipoSeleccionado == TipoEquipaje.MALETA) {
                                    System.out.print("Ingrese la cantidad de piezas de " + tipoSeleccionado.name() + ": ");
                                    cantidadPiezas = sc.nextInt();
                                    sc.nextLine();
                                }

                                Equipaje nuevoEquipaje = new Equipaje(tipoSeleccionado, precioBase, cantidadPiezas);
                                listaEquipaje.add(nuevoEquipaje);
                                System.out.println("Equipaje agregado. Precio base: $" + (precioBase * cantidadPiezas));
                            }

                            double precioFinalPasaje = gestor.calcularPrecioIndividualPasajero(vuelo, asientoElegido, listaEquipaje);

                            System.out.println("Precio individual calculado: $" + precioFinalPasaje);


                            DetallePasajero detalle = new DetallePasajero(pasajeroPrincipal, asientoElegido, listaEquipaje, precioFinalPasaje);
                            detalles.add(detalle);
                        }


                        System.out.println("→ Procesando reserva y calculando precio total...");

                        gestor.crearReserva(pasajeroPrincipal.getDni(), idVuelo, metodoDePago, detalles);

                        System.out.println("Reserva creada con éxito para " + cantidadPasajes + " pasaje(s).");


                    } catch (java.time.format.DateTimeParseException e) {
                        System.out.println("ERROR: Formato de fecha incorrecto. Use YYYY-MM-DD.");
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("ERROR: Ingrese solo números válidos. Intente de nuevo.");
                        sc.nextLine();
                    } catch (excepciones.IdNoExistenteException | excepciones.ValorInvalidoException |
                             excepciones.AsientoNoDisponibleException | excepciones.IdDuplicadoException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("ERROR INESPERADO: " + e.getMessage());
                    }
                    break;
                /*case 2:
                    System.out.println("→ Modificando reserva...");
                    // empleado.modificarReserva();
                    break;
                case 3:
                    System.out.println("→ Eliminando reserva...");
                    // empleado.eliminarReserva();
                    break;*/
                case 4:
                    sc.nextLine();
                    System.out.print("Ingrese el DNI del pasajero: ");
                    String dniPasajero = sc.nextLine();

                    System.out.print("Ingrese nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Ingrese apellido: ");
                    String apellido = sc.nextLine();

                    System.out.print("Ingrese dirección: ");
                    String direccion = sc.nextLine();

                    System.out.print("Ingrese teléfono: ");
                    long telefono = sc.nextLong();
                    sc.nextLine();

                    System.out.print("Ingrese email: ");
                    String email = sc.nextLine();

                    System.out.print("Ingrese fecha de nacimiento (YYYY-MM-DD, ej: 1995-03-01): ");
                    String fechaNacimiento = sc.nextLine();
                    LocalDate fechaNac = LocalDate.parse(fechaNacimiento);

                    System.out.print("Ingrese nombre de usuario: ");
                    String usuario = sc.nextLine();

                    System.out.print("Ingrese contraseña: ");
                    String password = sc.nextLine();

                    Pasajero pasajeroPrincipal = new Pasajero(dniPasajero, nombre, apellido, direccion, telefono, email, fechaNac, usuario, password);

                    System.out.println("→ Registrando nuevo pasajero...");
                    gestor.crearPersona(dniPasajero, pasajeroPrincipal);

                    System.out.println("Pasajero registrado con éxito.");

                case 5:
                    System.out.println("→ Cerrando sesión...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 5);
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
                        System.out.println("Ingrese la clave del vuelo:ej. VUELO-A-01");
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
                            System.out.println("ID: " + id);
                        }
                        String idAvion = sc.nextLine();

                        System.out.println("Ingrese el id del Aeropuerto de origen. Estos son los ID disponibles");
                        Set<String> idsAeropuertosOrigen = gestor.leerTodosLosIdsAeropuerto();
                        for (String id : idsAeropuertosOrigen) {
                            System.out.println("ID: " + id);
                        }
                        String idOrigen = sc.nextLine();

                        System.out.println("Ingrese el id del Aeropuerto de destino. Estos son los ID disponibles");
                        Set<String> idsAeropuertosDestino = gestor.leerTodosLosIdsAeropuerto();
                        for (String id : idsAeropuertosDestino) {
                            System.out.println("ID: " + id);
                        }
                        String idDestino = sc.nextLine();

                        Vuelo nuevoVuelo = new Vuelo(idVuelo, null, null, fechaHora, duracion, null, new HashMap<>(), precio);

                        System.out.println("→ Creando vuelo...");
                        gestor.crearVuelo(idVuelo, idAvion, idOrigen, idDestino, nuevoVuelo);
                    } catch (java.time.format.DateTimeParseException e) {
                        System.out.println("ERROR: Formato de fecha/hora incorrecto. Use YYYY-MM-DDTHH:MM.");
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("ERROR: Ingrese solo números para duración y precio.");
                        sc.nextLine();
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
                        System.out.println("Ingrese el DNI del empleado a crear");
                        String dni = sc.nextLine();

                        System.out.print("Ingrese nombre del empleado: ");
                        String nombre = sc.nextLine();

                        System.out.println("Ingrese el apellido del empleado ");
                        String apellido = sc.nextLine();

                        System.out.print("Ingrese direccion del empleado: ");
                        String direccion = sc.nextLine();

                        System.out.println("Ingrese el telefono del empleado ");
                        long telefono = sc.nextLong();
                        sc.nextLine();

                        System.out.print("Ingrese email del empleado: ");
                        String email = sc.nextLine();

                        System.out.print("Ingrese fecha y hora de nacimiento (YYYY-MM-DD, ej: 1995-03-01): ");
                        String fechaNacimiento = sc.nextLine();
                        LocalDate fechaNac = LocalDate.parse(fechaNacimiento);

                        System.out.println("Ingrese el usuario del empleado ");
                        String usuario = sc.nextLine();

                        System.out.print("Ingrese password del empleado: ");
                        String password = sc.nextLine();

                        System.out.println("Ingrese la fecha de ingreso del empleado YYYY-MM-DD, ej: 2025-12-01");
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