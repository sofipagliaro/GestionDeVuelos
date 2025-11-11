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




}
