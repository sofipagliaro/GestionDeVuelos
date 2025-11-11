package clases;

import manejoJSON.*;

import java.util.HashMap;

public class GestionAerolinea {
    private HashMap<String, Aeropuerto> mapaAeropuertos;
    private HashMap<String, Avion> mapaAviones;
    private HashMap<String, Persona> mapaPersonas;
    private HashMap<String, Vuelo> mapaVuelos;
    private HashMap<String, Reserva> mapaReservas;

    public GestionAerolinea(HashMap<String, Aeropuerto> mapaAeropuertos, HashMap<String, Avion> mapaAviones, HashMap<String, Persona> mapaPersonas, HashMap<String, Vuelo> mapaVuelos, HashMap<String, Reserva> mapaReservas) {
        this.mapaAeropuertos = GestionJSONAeropuerto.mapeoAeropuertos();
        this.mapaAviones = GestionJSONAvion.mapeoAviones();
        this.mapaPersonas = GestionJSONPersona.mapeoPersonas();
        this.mapaVuelos = GestionJSONVuelo.mapeoVuelos();
        this.mapaReservas = GestionJSONReserva.mapeoReservas();
    }


}
