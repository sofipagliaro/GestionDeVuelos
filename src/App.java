import clases.Aeropuerto;
import clases.Avion;
import clases.Vuelo;
import manejoJSON.GestionJSONAeropuerto;
import manejoJSON.GestionJSONAvion;
import manejoJSON.GestionJSONVuelo;

import java.util.HashMap;

public class App {
    public static void main(String[] args) {

        HashMap<String, Aeropuerto> mapaAeropuertos = GestionJSONAeropuerto.mapeoAeropuertos();
        HashMap<String, Avion> mapaAviones = GestionJSONAvion.mapeoAviones();
        HashMap<String, Vuelo> mapaVuelos = GestionJSONVuelo.mapeoVuelos(mapaAviones, mapaAeropuertos);

        Vuelo v = new Vuelo();
        int total = v.calcularTotalPasajeros(mapaVuelos);

        System.out.println(total);

        




    }
}
