package clases;

import interfaces.I_VerViajes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pasajero extends Persona implements I_VerViajes {
    public Pasajero(String dni, String nombre, String apellido, String direccion, long telefono, String email, LocalDate fechaNacimiento, String usuario, String password, List<Reserva> reservas) {
        super(dni, nombre, apellido, direccion, telefono, email, fechaNacimiento, usuario, password);
        this.reservas = reservas;
    }

    public Pasajero(String dni, String nombre, String apellido, String direccion, long telefono, String email, LocalDate fechaNacimiento, String usuario, String password) {
        super(dni, nombre, apellido, direccion, telefono, email, fechaNacimiento, usuario, password);
    }

    private List<Reserva> reservas;


    /// CONSTRUCTOR
    public Pasajero() {
        this.reservas = new ArrayList<>();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }



    @Override
    public List<Vuelo> verVuelos() {
        LocalDateTime ahora = LocalDateTime.now();
        List<Vuelo> vuelosAntiguos = new ArrayList<>();

        for (Reserva r : reservas) {
            Vuelo v = r.getVuelo();
            if (v.getFechaHora().isBefore(ahora)) {
                vuelosAntiguos.add(v);
            }
        }

        if (vuelosAntiguos.isEmpty()) {
            System.out.println("No tenés vuelos antiguos registrados.");
        } else {
            System.out.println("✈️  Tus vuelos realizados:");
            for (Vuelo v : vuelosAntiguos) {
                System.out.println("ID: " + v.getIdVuelo() +
                        " | Origen: " + v.getOrigen().getNombre() +
                        " | Destino: " + v.getDestino().getNombre() +
                        " | Fecha: " + v.getFechaHora());
            }
        }

        return vuelosAntiguos;
    }

    @Override
    public List<Reserva> verReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No tenés reservas registradas.");
        } else {
            System.out.println("📘 Tus reservas:");
            for (Reserva r : reservas) {
                System.out.println("ID Reserva: " + r.getIdReserva() +
                        " | Vuelo: " + r.getVuelo().getIdVuelo() +
                        " | Fecha: " + r.getFechaHora() +
                        " | Total: $" + r.getPrecioTotal());
            }
        }

        return reservas;
    }

    @Override
    public String toString() {
        return "Pasajero{" +
                "reservas=" + reservas +
                "} " + super.toString();
    }
}
