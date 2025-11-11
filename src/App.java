import clases.*;
import gestores.GestionAerolinea;
import manejoJSON.*;

import java.util.HashMap;

public class App {
    public static void main(String[] args) {

        HashMap<String, Aeropuerto> mapaAeropuertos = GestionJSONAeropuerto.mapeoAeropuertos();
        HashMap<String, Avion> mapaAviones = GestionJSONAvion.mapeoAviones();
        HashMap<String, Vuelo> mapaVuelos = GestionJSONVuelo.mapeoVuelos(mapaAviones, mapaAeropuertos);

        HashMap<String, Persona> mapaPersonas = GestionJSONPersona.mapeoPersonas();

        HashMap<String, Reserva> mapaReservas = GestionJSONReserva.mapeoReservas(mapaPersonas, mapaVuelos);

        GestionAerolinea gestor = new GestionAerolinea();

        System.out.println(mapaReservas);
        System.out.println(mapaReservas.size());

        System.out.println(mapaPersonas);
        System.out.println(mapaPersonas.size());

        Vuelo v = new Vuelo();
        int total = v.calcularTotalPasajeros(mapaVuelos);

        System.out.println(total);

        




    }
}
