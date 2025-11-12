package manejoJSON;

import clases.Aeropuerto;
import clases.Avion;
import clases.Vuelo;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;

public class GestionJSONVuelo {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public static HashMap<String, Vuelo> mapeoVuelos(HashMap<String, Avion> mapaAviones, HashMap<String, Aeropuerto> mapaAeropuertos) {
        HashMap<String, Vuelo> mapaVuelos = new HashMap<>();

        try {
            JSONObject jVuelos = new JSONObject(JSONUtiles.leer("vuelos.json"));
            Iterator<String> clave = jVuelos.keys();

            while (clave.hasNext()){
                String idVuelo = clave.next();
                JSONObject jVuelo = jVuelos.getJSONObject(idVuelo);

                Vuelo v = mapeoVuelo(jVuelo, mapaAviones, mapaAeropuertos);
                mapaVuelos.put(idVuelo, v);

            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return mapaVuelos;
    }

    public static Vuelo mapeoVuelo(JSONObject jVuelo, HashMap<String, Avion> mapaAviones, HashMap<String, Aeropuerto> mapaAeropuertos) {
        Vuelo v = new Vuelo();

        try {
            v.setIdVuelo(jVuelo.getString("idVuelo"));

            // Origen y destino
            String codigoOrigen = jVuelo.getString("idOrigen");
            String codigoDestino = jVuelo.getString("idDestino");
            v.setOrigen(mapaAeropuertos.get(codigoOrigen));
            v.setDestino(mapaAeropuertos.get(codigoDestino));

            // Fecha y duración
            String fechaHoraString = jVuelo.getString("fechaHora");
            v.setFechaHora(LocalDateTime.parse(fechaHoraString, DATE_FORMATTER));
            v.setDuracion(jVuelo.getInt("duracion"));

            // Avión
            String idAvion = jVuelo.getString("idAvion");
            v.setAvion(mapaAviones.get(idAvion));

            // Asientos reservados (si existen)
            JSONObject jAsientos = jVuelo.getJSONObject("asientosReservados");
            HashMap<Integer, Boolean> mapaAsientos = new HashMap<>();
            Iterator<String> keys = jAsientos.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                mapaAsientos.put(Integer.parseInt(key), jAsientos.getBoolean(key));
            }
            v.setAsientosReservados(mapaAsientos);

            v.setPrecio(jVuelo.getDouble("precio"));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return v;
    }

}