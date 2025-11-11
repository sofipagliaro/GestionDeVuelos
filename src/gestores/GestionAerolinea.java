package gestores;

import clases.*;
import enums.MetodoDePago;
import excepciones.*;
import manejoJSON.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GestionAerolinea {
    private ClaseGenerica<Aeropuerto> gestorAeropuertos;
    private ClaseGenerica<Avion> gestorAviones;
    private ClaseGenerica<Persona> gestorPersonas;
    private ClaseGenerica<Vuelo> gestorVuelos;
    private ClaseGenerica<Reserva> gestorReservas;
    private int contadorReservas;

    public GestionAerolinea() {
        cargarDatosInicialesYGestores();
    }

    private void cargarDatosInicialesYGestores() {

        HashMap<String, Aeropuerto> inicialAeropuertos = GestionJSONAeropuerto.mapeoAeropuertos();
        HashMap<String, Avion> inicialAviones = GestionJSONAvion.mapeoAviones();
        HashMap<String, Persona> inicialPersonas = GestionJSONPersona.mapeoPersonas();

        this.gestorAeropuertos = new ClaseGenerica<>("aeropuertos.json", inicialAeropuertos);
        this.gestorAviones = new ClaseGenerica<>("aviones.json", inicialAviones);
        this.gestorPersonas = new ClaseGenerica<>("personas.json", inicialPersonas);

        HashMap<String, Vuelo> inicialVuelos = GestionJSONVuelo.mapeoVuelos(gestorAviones.getMapaEntidades(), gestorAeropuertos.getMapaEntidades());
        this.gestorVuelos = new ClaseGenerica<>("vuelos.json", inicialVuelos);

        HashMap<String, Reserva> inicialReservas = GestionJSONReserva.mapeoReservas(gestorPersonas.getMapaEntidades(), gestorVuelos.getMapaEntidades());
        this.gestorReservas = new ClaseGenerica<>("reservas.json", inicialReservas);

        this.contadorReservas = calcularSiguienteIdReserva();

        System.out.println("Sistema inicializado y gestores listos.");
    }


    /// AEROLINEAS

    public void crearAeropuerto(String codigoAeropuertoClave, Aeropuerto nuevoAeropuerto) throws IdDuplicadoException {
        this.gestorAeropuertos.agregar(codigoAeropuertoClave, nuevoAeropuerto);
        System.out.println("Aeropuerto " + codigoAeropuertoClave + " creado y guardado.");
    }

    public Aeropuerto obtenerAeropuertoPorId(String codigoAeropuerto) throws IdNoExistenteException {
        return this.gestorAeropuertos.leer(codigoAeropuerto);
    }

    public void modificarAeropuerto(String codigoAeropuertoClave, Aeropuerto aeropuertoModificado) throws IdNoExistenteException {
        this.gestorAeropuertos.actualizar(codigoAeropuertoClave, aeropuertoModificado);
        System.out.println("Aeropuerto " + codigoAeropuertoClave + " modificado y guardado.");
    }

    public void eliminarAeropuerto(String codigoAeropuerto) throws IdNoExistenteException, AeropuertoEnUsoException {

        if (tieneVuelosPendientes(codigoAeropuerto)) {
            throw new AeropuertoEnUsoException("El Aeropuerto " + codigoAeropuerto + " no puede ser eliminado porque tiene vuelos programados pendientes."
            );
        }

        this.gestorAeropuertos.eliminar(codigoAeropuerto);
        System.out.println("Aeropuerto " + codigoAeropuerto + " eliminado del sistema y del JSON.");
    }

    private boolean tieneVuelosPendientes(String codigoAeropuerto) {

        HashMap<String, Vuelo> todosLosVuelos = this.gestorVuelos.getMapaEntidades();
        LocalDateTime ahora = LocalDateTime.now();

        for (Vuelo vuelo : todosLosVuelos.values()) {

            boolean usaAeropuerto = vuelo.getOrigen().getCodigo().equals(codigoAeropuerto) || vuelo.getDestino().getCodigo().equals(codigoAeropuerto);

            boolean esPendiente = vuelo.getFechaHora().isAfter(ahora);

            if (usaAeropuerto && esPendiente) {
                return true; // Hay vuelos pendientes
            }
        }

        return false; // No hay vuelos pendientes
    }

    /// AVIONES

    public void crearAvion(String idAvionClave, Avion nuevoAvion) throws IdDuplicadoException {
        this.gestorAviones.agregar(idAvionClave, nuevoAvion);
        System.out.println("Avión " + idAvionClave + " creado y guardado.");
    }

    public Avion obtenerAvionPorId(String idAvion) throws IdNoExistenteException {
        return this.gestorAviones.leer(idAvion);
    }

    public void modificarAvion(String idAvionClave, Avion avionModificado) throws IdNoExistenteException {
        this.gestorAviones.actualizar(idAvionClave, avionModificado);
        System.out.println("Avión " + idAvionClave + " modificado y guardado.");
    }

    public void eliminarAvion(String idAvion) throws IdNoExistenteException, AvionEnUsoException {

        if (tieneVuelosPendientesAsignados(idAvion)) {
            throw new AvionEnUsoException("El Avión " + idAvion + " no puede ser eliminado porque tiene vuelos programados pendientes asignados.");
        }

        this.gestorAviones.eliminar(idAvion);
        System.out.println("Avión " + idAvion + " eliminado del sistema y del JSON.");
    }

    private boolean tieneVuelosPendientesAsignados(String idAvion) {
        HashMap<String, Vuelo> todosLosVuelos = this.gestorVuelos.getMapaEntidades();
        LocalDateTime ahora = LocalDateTime.now();

        for (Vuelo vuelo : todosLosVuelos.values()) {
            boolean usaAvion = vuelo.getAvion().getIdAvion().equals(idAvion);
            boolean esPendiente = vuelo.getFechaHora().isAfter(ahora);

            if (usaAvion && esPendiente) {
                return true; // Hay vuelos pendientes con ese avión
            }
        }

        return false; // No hay vuelos pendientes con ese avión
    }

    /// PERSONAS

    public void crearPersona(String idPersonaClave, Persona nuevaPersona) throws IdDuplicadoException {
        this.gestorPersonas.agregar(idPersonaClave, nuevaPersona);
        System.out.println("Persona (DNI: " + idPersonaClave + ") creada y guardada.");
    }

    public Persona obtenerPersonaPorId(String idPersona) throws IdNoExistenteException {
        return this.gestorPersonas.leer(idPersona);
    }

    public void modificarPersona(String idPersonaClave, Persona personaModificada) throws IdNoExistenteException {
        this.gestorPersonas.actualizar(idPersonaClave, personaModificada);
        System.out.println("Persona (DNI: " + idPersonaClave + ") modificada y guardada.");
    }

    public void eliminarPersona(String idPersona) throws IdNoExistenteException, PersonaConReservasException {

        if (tieneReservasPendientes(idPersona)) {
            throw new PersonaConReservasException("La Persona con ID " + idPersona + " no puede ser eliminada porque tiene reservas de vuelo pendientes a su nombre.");
        }

        this.gestorPersonas.eliminar(idPersona);
        System.out.println("Persona (DNI: " + idPersona + ") eliminada del sistema y del JSON.");
    }

    private boolean tieneReservasPendientes(String idPersona) {

        HashMap<String, Reserva> todasLasReservas = this.gestorReservas.getMapaEntidades();
        LocalDateTime ahora = LocalDateTime.now();

        for (Reserva reserva : todasLasReservas.values()) {

            boolean esPendiente = reserva.getVuelo().getFechaHora().isAfter(ahora);

            if (esPendiente) {

                if (reserva.getPasajero().getDni().equals(idPersona)) {
                    return true;
                }

                List<DetallePasajero> detalles = reserva.getDetallePasajero();

                for (DetallePasajero detalle : detalles) {
                    if (detalle.getPasajero().getDni().equals(idPersona)) {
                        return true;
                    }
                }
            }
        }

        return false; // No hay reservas pendientes
    }

    /// VUELOS

    public void crearVuelo(String idVueloClave, String idAvion, String idOrigen, String idDestino, Vuelo nuevoVuelo
    ) throws IdDuplicadoException, IdNoExistenteException {

        Avion avionAsignado = this.gestorAviones.leer(idAvion);
        Aeropuerto origen = this.gestorAeropuertos.leer(idOrigen);
        Aeropuerto destino = this.gestorAeropuertos.leer(idDestino);

        nuevoVuelo.setAvion(avionAsignado);
        nuevoVuelo.setOrigen(origen);
        nuevoVuelo.setDestino(destino);

        this.gestorVuelos.agregar(idVueloClave, nuevoVuelo);
        System.out.println("Vuelo " + idVueloClave + " creado y guardado.");
    }

    public Vuelo obtenerVueloPorId(String idVuelo) throws IdNoExistenteException {
        return this.gestorVuelos.leer(idVuelo);
    }

    public List<Vuelo> obtenerVuelosPorCiudadesOrigenDestino(String ciudadOrigen, String ciudadDestino) {

        HashMap<String, Vuelo> todosLosVuelos = this.gestorVuelos.getMapaEntidades();
        List<Vuelo> vuelosEncontrados = new ArrayList<>();

        for (Vuelo vuelo : todosLosVuelos.values()) {

            String vueloCiudadOrigen = vuelo.getOrigen().getUbicacion().getCiudad();
            String vueloCiudadDestino = vuelo.getDestino().getUbicacion().getCiudad();

            if (vueloCiudadOrigen.equalsIgnoreCase(ciudadOrigen) &&
                    vueloCiudadDestino.equalsIgnoreCase(ciudadDestino)) {

                vuelosEncontrados.add(vuelo);
            }
        }

        return vuelosEncontrados;
    }

    public List<Vuelo> obtenerVuelosPorPrecioMaximo(double precioMaximo) {

        HashMap<String, Vuelo> todosLosVuelos = this.gestorVuelos.getMapaEntidades();
        List<Vuelo> vuelosEncontrados = new ArrayList<>();

        for (Vuelo vuelo : todosLosVuelos.values()) {

            double precioBaseVuelo = vuelo.getPrecio();

            if (precioBaseVuelo <= precioMaximo) {
                vuelosEncontrados.add(vuelo);
            }
        }

        return vuelosEncontrados;
    }

    public void modificarVuelo(String idVueloClave, Vuelo vueloModificado) throws IdNoExistenteException {

        String nuevoIdAvion = vueloModificado.getAvion().getIdAvion();
        this.gestorAviones.leer(nuevoIdAvion);

        String nuevoIdOrigen = vueloModificado.getOrigen().getCodigo();
        this.gestorAeropuertos.leer(nuevoIdOrigen);

        String nuevoIdDestino = vueloModificado.getDestino().getCodigo();
        this.gestorAeropuertos.leer(nuevoIdDestino);

        this.gestorVuelos.actualizar(idVueloClave, vueloModificado);
        System.out.println("Vuelo " + idVueloClave + " modificado y guardado.");
    }

    public void eliminarVuelo(String idVuelo) throws IdNoExistenteException, VueloConReservasException {
        if (tieneReservas(idVuelo)) {
            throw new VueloConReservasException("El Vuelo " + idVuelo + " no puede ser eliminado porque tiene reservas activas asociadas.");
        }

        this.gestorVuelos.eliminar(idVuelo);
        System.out.println("Vuelo " + idVuelo + " eliminado del sistema y del JSON.");
    }

    private boolean tieneReservas(String idVuelo) {
        HashMap<String, Reserva> todasLasReservas = this.gestorReservas.getMapaEntidades();
        for (Reserva reserva : todasLasReservas.values()) {
            if (reserva.getVuelo().getIdVuelo().equals(idVuelo)) {
                return true; // Se encontró una reserva para este vuelo
            }
        }

        return false; // No hay reservas
    }

    /// RESERVAS

    private int calcularSiguienteIdReserva() {
        int maxId = 0;

       for (String idString : this.gestorReservas.getMapaEntidades().keySet()) {
            try {
                int idNumerico = Integer.parseInt(idString);
                if (idNumerico > maxId) {
                    maxId = idNumerico;
                }
            } catch (NumberFormatException e) {
                // Manejar si hay claves inválidas que no sean números
                System.err.println("Advertencia: Clave de reserva no numérica encontrada: " + idString);
            }
        }
        // Devolvemos el máximo encontrado + 1 (empezando en 1, el primero será 1)
        return maxId + 1;
    }

    public Reserva crearReserva(String idPasajeroTitular, String idVuelo, MetodoDePago metodoPago, List<DetallePasajero> detallesPasajeros) throws IdNoExistenteException, ReservaNoPermitidaException{

        int idReservaNumerico = this.contadorReservas;
        String idReservaClave = String.valueOf(idReservaNumerico);

        Pasajero pasajeroTitular = (Pasajero) this.gestorPersonas.leer(idPasajeroTitular);
        Vuelo vueloSeleccionado = this.gestorVuelos.leer(idVuelo);

        int cantidadPasajeros = detallesPasajeros.size();

        double precioFinal = calcularPrecioTotalReserva(detallesPasajeros);

        LocalDateTime fechaPartidaVuelo = vueloSeleccionado.getFechaHora();
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        long horasRestantes = ChronoUnit.HOURS.between(fechaHoraActual, fechaPartidaVuelo);

        final long HORAS_MINIMAS_PARA_RESERVAR = 6;

        if (horasRestantes < HORAS_MINIMAS_PARA_RESERVAR) {
            throw new ReservaNoPermitidaException("No se puede crear la reserva para el vuelo " + idVuelo + " porque faltan menos de " + HORAS_MINIMAS_PARA_RESERVAR + " horas para la partida (Faltan: " + horasRestantes + " horas).");
        }

        Reserva nuevaReserva = new Reserva(idReservaNumerico, pasajeroTitular, metodoPago, fechaHoraActual, vueloSeleccionado, cantidadPasajeros, detallesPasajeros, precioFinal);

        this.gestorReservas.agregar(idReservaClave, nuevaReserva);

        this.contadorReservas++;

        System.out.println("Reserva " + idReservaClave + " creada por $" + precioFinal + " y guardada.");
        return nuevaReserva;
    }

    public double calcularPrecioIndividualPasajero(Vuelo vueloSeleccionado, Asiento asientoElegido, List<Equipaje> listaEquipajes) {
        double costoTarifaBase = vueloSeleccionado.getPrecio();
        double costoAsiento = asientoElegido.getPrecio();
        double costoTotalEquipaje = 0.0;
        for (Equipaje equipaje : listaEquipajes) {
            costoTotalEquipaje += equipaje.getPrecio();
        }

        double precioIndividual = costoTarifaBase + costoAsiento + costoTotalEquipaje;

        return precioIndividual;
    }

    private double calcularPrecioTotalReserva(List<DetallePasajero> detallesPasajeros) {
        double precioTotal = 0.0;

        for (DetallePasajero detalle : detallesPasajeros) {
            precioTotal += detalle.getPrecioIndividual();
        }

        return precioTotal;
    }

    public Reserva obtenerReservaPorId(String idReserva) throws IdNoExistenteException {
        return this.gestorReservas.leer(idReserva);
    }

    public void eliminarReserva(String idReserva) throws IdNoExistenteException, CancelacionNoPermitidaException { // O RuntimeException

        Reserva reservaAEliminar = this.gestorReservas.leer(idReserva);

        // No se pueden eliminar vuelos cuando faltan 3 días para el mismo

        LocalDateTime fechaPartidaVuelo = reservaAEliminar.getVuelo().getFechaHora();
        LocalDateTime ahora = LocalDateTime.now();

        long horasRestantes = ChronoUnit.HOURS.between(ahora, fechaPartidaVuelo);

        final long HORAS_MINIMAS_PARA_CANCELAR = 72;

        if (horasRestantes < HORAS_MINIMAS_PARA_CANCELAR) {
            throw new CancelacionNoPermitidaException("No se puede cancelar la reserva " + idReserva + " porque faltan " + horasRestantes + " horas para la partida (el límite es de " + HORAS_MINIMAS_PARA_CANCELAR + " horas).");
        }

        this.gestorReservas.eliminar(idReserva);
        System.out.println("Reserva " + idReserva + " cancelada con éxito y eliminada del JSON.");
    }


    /// LOGIN

    public Persona autenticarUsuario(String usuario, String password) throws InicioSesionInvalidoException {

        HashMap<String, Persona> mapaPersonas = this.gestorPersonas.getMapaEntidades();

        for (Persona persona : mapaPersonas.values()) {

            if (persona.getUsuario().equals(usuario)) {

                if (persona.getPassword().equals(password)) {
                    return persona;
                } else {

                    throw new InicioSesionInvalidoException("Usuario o contraseña incorrectos.");
                }
            }
        }

        throw new InicioSesionInvalidoException("Usuario o contraseña incorrectos.");
    }


}
