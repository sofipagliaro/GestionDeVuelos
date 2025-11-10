package clases;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Pasajero extends Persona{
    private boolean assistCard;
    private boolean priorityPass;

    /// CONSTRUCTOR
    public Pasajero() {
    }

    /// GETTERS Y SETTERS
    public boolean isAssistCard() {
        return assistCard;
    }

    public void setAssistCard(boolean assistCard) {
        this.assistCard = assistCard;
    }

    public boolean isPriorityPass() {
        return priorityPass;
    }

    public void setPriorityPass(boolean priorityPass) {
        this.priorityPass = priorityPass;
    }

    /// Metdos
    public List<Vuelo> buscarVuelosDisponibles(HashMap<String, Vuelo> mapaVuelos, String ciudadOrigen, String ciudadDestino, LocalDate fecha
    ) {
        List<Vuelo> vuelosDisponibles = new ArrayList<>();

        for (Vuelo vuelo : mapaVuelos.values()) {

            // Validar coincidencia de ciudad origen/destino
            String origenVuelo = vuelo.getOrigen().getUbicacion().getCiudad();
            String destinoVuelo = vuelo.getDestino().getUbicacion().getCiudad();

            boolean coincideOrigen = origenVuelo.equalsIgnoreCase(ciudadOrigen);
            boolean coincideDestino = destinoVuelo.equalsIgnoreCase(ciudadDestino);

            // Validar coincidencia de fecha (solo día, no hora)
            boolean coincideFecha = vuelo.getFechaHora().toLocalDate().isEqual(fecha);

            if (coincideOrigen && coincideDestino && coincideFecha) {
                vuelosDisponibles.add(vuelo);
            }
        }

        return vuelosDisponibles;
    }

    @Override
    public String toString() {
        return "Pasajero{" +
                "assistCard=" + assistCard +
                ", priorityPass=" + priorityPass +
                "} " + super.toString();
    }
}
