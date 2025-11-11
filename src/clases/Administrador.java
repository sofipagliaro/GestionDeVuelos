package clases;

import interfaces.I_VerViajes;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Administrador extends Empleado implements I_VerViajes {



    private List<Vuelo> vuelosGestionados;
    private List<Reserva> reservasRegistradas;

    public Administrador() {
        this.vuelosGestionados = new ArrayList<>();
        this.reservasRegistradas = new ArrayList<>();
    }
    public List<Vuelo> getVuelosGestionados() {
        return vuelosGestionados;
    }

    public List<Reserva> getReservasRegistradas() {
        return reservasRegistradas;
    }

    @Override
    public
    List<Vuelo> verVuelos() {
        return vuelosGestionados;
    }

    @Override
    public List<Reserva> verReservas() {
        return reservasRegistradas;
    }

}
