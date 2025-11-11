package manejoJSON;

import clases.*;
import enums.MetodoDePago;
import enums.TipoEquipaje;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GestionJSONReserva {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static HashMap<String, Reserva> mapeoReservas(HashMap<String, Persona> mapaPersonas, HashMap<String, Vuelo> mapaVuelos) {

        HashMap<String, Reserva> mapaReservas = new HashMap<>();

        try {
            JSONObject jReservas = new JSONObject(JSONUtiles.leer("reservas.json"));
            Iterator<String> clave = jReservas.keys();

            while (clave.hasNext()) {
                String idReserva = clave.next();
                JSONObject jReserva = jReservas.getJSONObject(idReserva);

                Reserva r = mapeoReserva(jReserva, mapaPersonas, mapaVuelos);
                mapaReservas.put(idReserva, r);
            }

        } catch (JSONException e) {
            throw new RuntimeException();
        }
        return mapaReservas;
    }

    public static Reserva mapeoReserva(JSONObject jReserva, HashMap<String, Persona> mapaPersonas, HashMap<String, Vuelo> mapaVuelos) {
        Reserva r = new Reserva();

        try {

            String dniPasajeroTitular = jReserva.getString("dniPasajeroTitular");
            String idVuelo = jReserva.getString("idVuelo");

            if (mapaPersonas.containsKey(dniPasajeroTitular) && mapaPersonas.get(dniPasajeroTitular) instanceof Pasajero) {
                r.setPasajero((Pasajero) mapaPersonas.get(dniPasajeroTitular));
            } else {
                throw new RuntimeException("Pasajero titular con DNI " + dniPasajeroTitular + " no encontrado o no es un Pasajero.");
            }

            Vuelo vuelo = mapaVuelos.get(idVuelo);
            if (vuelo != null) {
                r.setVuelo(vuelo);
            } else {
                throw new RuntimeException("Vuelo con ID " + idVuelo + " no encontrado.");
            }

            r.setIdReserva(jReserva.getInt("idReserva"));
            r.setMetodoDePago(MetodoDePago.valueOf(jReserva.getString("metodoDePago")));
            r.setFechaHora(LocalDateTime.parse(jReserva.getString("fechaHora"), DATE_TIME_FORMATTER));
            r.setCantidad(jReserva.getInt("cantidad"));
            r.setPrecioTotal(jReserva.getDouble("precioTotal"));

            JSONArray jDetalles = jReserva.getJSONArray("detallePasajero");
            List<DetallePasajero> listaDetalles = mapeoDetallePasajeros(jDetalles, mapaPersonas);
            r.setDetallePasajero(listaDetalles);

        } catch (JSONException e) {
            throw new RuntimeException();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException();
        }
        return r;
    }

    private static List<DetallePasajero> mapeoDetallePasajeros(JSONArray jDetalles, HashMap<String, Persona> mapaPersonas) {
        List<DetallePasajero> listaDetalles = new ArrayList<>();

        for (int i = 0; i < jDetalles.length(); i++) {
            try {
                JSONObject jDetalle = jDetalles.getJSONObject(i);

                DetallePasajero dp = new DetallePasajero();

                String dniDetalle = jDetalle.getString("dniPasajero");
                if (mapaPersonas.containsKey(dniDetalle) && mapaPersonas.get(dniDetalle) instanceof Pasajero) {
                    dp.setPasajero((Pasajero) mapaPersonas.get(dniDetalle));
                } else {
                    throw new RuntimeException("Pasajero del detalle con DNI " + dniDetalle + " no encontrado o no es un Pasajero.");
                }

                String idAsiento = jDetalle.getString("asientoAsignado");
                int idAsientoInt = Integer.parseInt(idAsiento);
                Asiento asiento = new Asiento();
                asiento.setIdAsiento(idAsientoInt);
                dp.setAsientoAsignado(asiento);

                dp.setPrecioIndividual(jDetalle.getDouble("precioIndividual"));

                JSONArray jEquipajes = jDetalle.getJSONArray("listaEquipaje");
                dp.setListaEquipaje(mapeoEquipajes(jEquipajes));

                listaDetalles.add(dp);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return listaDetalles;
    }

    private static List<Equipaje> mapeoEquipajes(JSONArray jEquipajes) {
        List<Equipaje> listaEquipaje = new ArrayList<>();

        for (int j = 0; j < jEquipajes.length(); j++) {

            try {
            JSONObject jEquipaje = jEquipajes.getJSONObject(j);

            Equipaje eq = new Equipaje();

            eq.setTipoEquipaje(TipoEquipaje.valueOf(jEquipaje.getString("tipoEquipaje")));
            eq.setPrecio(jEquipaje.getDouble("precio"));
            eq.setCantidad(jEquipaje.getInt("cantidad"));

            listaEquipaje.add(eq);

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return listaEquipaje;
    }
}

