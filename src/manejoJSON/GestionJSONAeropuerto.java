package manejoJSON;

import clases.Aeropuerto;
import clases.Ubicacion;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class GestionJSONAeropuerto {

    public static HashMap<String, Aeropuerto> mapeoAeropuertos() {
        HashMap<String, Aeropuerto> mapaAeropuertos = new HashMap<>();

        try {
            JSONObject jAeropuertos = new JSONObject(JSONUtiles.leer("aeropuertos.json"));
            Iterator<String> clave = jAeropuertos.keys();

            while (clave.hasNext()) {
                String codigo = clave.next();

                JSONObject jAeropuerto = jAeropuertos.getJSONObject(codigo);
                Aeropuerto a = mapeoAeropuerto(jAeropuerto);

                mapaAeropuertos.put(codigo, a);

            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return mapaAeropuertos;
    }

    public static Aeropuerto mapeoAeropuerto(JSONObject jAeropuerto){
        Aeropuerto a = new Aeropuerto();

        try {
            a.setCodigo(jAeropuerto.getString("codigo"));
            a.setNombre(jAeropuerto.getString("nombre"));

            JSONObject jUbicacion = jAeropuerto.getJSONObject("ubicacion");
            Ubicacion u = mapeoUbicacion(jUbicacion);

            a.setUbicacion(u);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return a;
    }

    public static Ubicacion mapeoUbicacion(JSONObject jUbicacion){
        Ubicacion u = new Ubicacion();

        try {
            u.setCiudad(jUbicacion.getString("ciudad"));
            u.setProvincia(jUbicacion.getString("provincia"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return u;
    }

}

