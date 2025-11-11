import clases.Aeropuerto;
import clases.Avion;
import manejoJSON.GestionJSONAeropuerto;
import manejoJSON.GestionJSONAvion;

import java.util.HashMap;

public class App {
    public static void main(String[] args) {

        HashMap<String, Aeropuerto> mapaAeropuertos = GestionJSONAeropuerto.mapeoAeropuertos();
        HashMap<String, Avion> mapaAviones = GestionJSONAvion.mapeoAviones();




    }
}
