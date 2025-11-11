package clases;

import interfaces.I_VerViajes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Pasajero extends Persona implements I_VerViajes {
    private boolean assistCard;
    private boolean priorityPass;
    private List<Reserva> reservas;

    /// CONSTRUCTOR
    public Pasajero() {
        this.reservas = new ArrayList<>();
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

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public List<Vuelo> verVuelos() {
        List<Vuelo> vuelos = new ArrayList<>();
        for (Reserva r : reservas) {
            vuelos.add(r.getVuelo());
        }
        return vuelos;
    }

    @Override
    public List<Reserva> verReservas() {
        return reservas;
    }


    @Override
    public String toString() {
        return "Pasajero{" +
                "assistCard=" + assistCard +
                ", priorityPass=" + priorityPass +
                "} " + super.toString();
    }
}
