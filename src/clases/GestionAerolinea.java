package clases;

import enums.MetodoDePago;
import excepciones.CancelacionNoPermitidaException;
import excepciones.IdDuplicadoException;
import excepciones.IdNoExistenteException;
import excepciones.ReservaNoPermitidaException;
import manejoJSON.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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


}
