package clases;

import interfaces.I_VerViajes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Pasajero extends Persona implements I_VerViajes {
    private List<Reserva> reservas;

    /// CONSTRUCTOR
    public Pasajero() {
        this.reservas = new ArrayList<>();
    }

    @Override
    public List<Vuelo> verVuelos() {
        return List.of();
    }

    @Override
    public List<Reserva> verReservas() {
        return List.of();
    }
}
