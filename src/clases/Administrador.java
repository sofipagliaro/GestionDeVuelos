package clases;

import interfaces.I_VerViajes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

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
    public List<Vuelo> verVuelos() {
        LocalDateTime ahora = LocalDateTime.now();

        List<Vuelo> vuelosAntiguos = vuelosGestionados.stream().filter(vuelo -> vuelo.getFechaHora().isBefore(ahora)).collect(Collectors.toList());

        if (vuelosAntiguos.isEmpty()) {
            System.out.println("No hay vuelos antiguos registrados.");
        } else {
            System.out.println("Vuelos antiguos gestionados:");
            vuelosAntiguos.forEach(v ->
                    System.out.println("ID: " + v.getIdVuelo() +
                            " | Origen: " + v.getOrigen().getNombre() +
                            " | Destino: " + v.getDestino().getNombre() +
                            " | Fecha: " + v.getFechaHora())
            );
        }

        return vuelosAntiguos;
    }

    @Override
    public List<Reserva> verReservas() {
        if (reservasRegistradas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
        } else {
            System.out.println("Reservas creadas:");
            for (Reserva r : reservasRegistradas) {
                System.out.println("ID Reserva: " + r.getIdReserva() +
                        " | Pasajero: " + r.getPasajero().getNombre() +
                        " | Vuelo: " + r.getVuelo().getIdVuelo() +
                        " | Fecha: " + r.getFechaHora() +
                        " | Total: $" + r.getPrecioTotal());
            }
        }

        return reservasRegistradas;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "vuelosGestionados=" + vuelosGestionados +
                ", reservasRegistradas=" + reservasRegistradas +
                "} " + super.toString();
    }
}
