package manejoJSON;

import clases.Aeropuerto;
import clases.Avion;
import clases.Ubicacion;
import clases.Vuelo;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class GestionJSONVuelo {

    public static HashMap<String, Vuelo> mapeoVuelos(HashMap<String, Avion> mapaAviones, HashMap<String, Aeropuerto> mapaAeropuertos) {
        HashMap<String, Vuelo> mapaVuelos = new HashMap<>();

        try {
            JSONObject jVuelos = new JSONObject(JSONUtiles.leer("vuelos.json"));
            Iterator<String> clave = jVuelos.keys();

            while (clave.hasNext()){
                String idVuelo = clave.next();
                JSONObject jVuelo = jVuelos.getJSONObject(idVuelo);

                Vuelo v = mapeoVuelo(jVuelo);
                mapaVuelos.put(idVuelo, v);

            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return mapaVuelos;
    }

    public static Vuelo mapeoVuelo(JSONObject jVuelo, HashMap<String, Avion> mapaAviones, HashMap<String, Aeropuerto> mapaAeropuertos){
        Vuelo v = new Vuelo();

        try {
            v.setIdVuelo(jVuelo.getString("idVuelo"));
            String codigoOrigen = jVuelo.getString("origen");
            Aeropuerto origen = mapaAeropuertos.get(codigoOrigen);

            /// NO LO TERMINE!!!

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return v;
    }
}