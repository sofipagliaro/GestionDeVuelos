package manejoJSON;

import clases.Asiento;
import clases.Avion;
import enums.EstadoAvion;
import enums.TamanioAvion;
import enums.TipoClase;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;

public class GestionJSONAvion {

    public static HashMap<String, Avion> mapeoAviones(){
        HashMap<String, Avion> mapaAviones = new HashMap<>();

        try {
            JSONObject jAviones = new JSONObject(JSONUtiles.leer("aviones.json"));
            Iterator<String> clave = jAviones.keys();

            while (clave.hasNext()){
                String idAvion = clave.next();

                try {
                    JSONObject jAvion = jAviones.getJSONObject(idAvion);

                    Avion a = mapeoAvion(jAvion);
                    mapaAviones.put(idAvion, a);
                } catch(RuntimeException e){
                    continue;
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return mapaAviones;
    }

    public static Avion mapeoAvion(JSONObject jAvion){
        Avion a = new Avion();

        try {
            a.setIdAvion(jAvion.getString("idAvion"));

            try {
                String estadoAvionString = jAvion.getString("estadoAvion");
                a.setEstadoAvion(EstadoAvion.valueOf(estadoAvionString));
            } catch (IllegalArgumentException e){
               throw new IllegalArgumentException("Ese estado de avion no pertenece a la lista");
            }

            try {
                String tamanioAvionString = jAvion.getString("tamanioAvion");
                a.setTamanioAvion(TamanioAvion.valueOf(tamanioAvionString));
            } catch (IllegalArgumentException e){
                throw new IllegalArgumentException("Ese tamaño de avion no pertenece a la lista");
            }

            JSONObject jMapaAsientos = jAvion.getJSONObject("mapaAsientos");
            HashMap<Integer, Asiento> mapaAsientos = new HashMap<>();

            Iterator<String> clave = jMapaAsientos.keys();

            while (clave.hasNext()){
                String idAsiento = clave.next();
                JSONObject jAsiento = jMapaAsientos.getJSONObject(idAsiento);

                Asiento asiento = mapeoAsiento(jAsiento);

                mapaAsientos.put(Integer.parseInt(idAsiento), asiento);
            }

            a.setMapaAsientos(mapaAsientos);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return a;
    }

    public static Asiento mapeoAsiento (JSONObject jAsiento){
        Asiento asiento = new Asiento();

        try {
            asiento.setIdAsiento(jAsiento.getInt("idAsiento"));

            try {
                String tipoClaseString = jAsiento.getString("tipoClase");
                asiento.setTipoClase(TipoClase.valueOf(tipoClaseString));
            } catch (IllegalArgumentException e){
                throw new IllegalArgumentException("Ese tipo de clase de asiento no pertenece a la lista");
            }

            asiento.setPrecio(jAsiento.getDouble("precio"));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return asiento;
    }

}
