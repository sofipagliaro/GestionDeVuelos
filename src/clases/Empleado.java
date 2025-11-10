package clases;

import enums.MetodoDePago;
import enums.Puesto;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Empleado extends Persona {
    private Puesto puesto;
    private LocalDate fechaIngreso;

    /// CONSTRUCTOR
    public Empleado() {
    }

    /// GETTERS Y SETTERS
    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Reserva crearReserva(HashMap<Integer, Reserva> mapaReservas, int idReserva, Pasajero cliente, Vuelo vuelo, List<DetallePasajero> detallePasajero, MetodoDePago metodoDePago) {
        Reserva reserva = new Reserva();
        reserva.setIdReserva(idReserva);
        reserva.setCliente(cliente);
        reserva.setVuelo(vuelo);
        reserva.setDetallePasajero(detallePasajero);
        reserva.setMetodoDePago(metodoDePago);
        reserva.setFechaHora(LocalDateTime.now());

        // Calcular precio total (según los asientos del detalle)
        double precioTotal = 0;
        for (DetallePasajero d : detallePasajero) {
            precioTotal += d.getPrecioIndividual();
        }
        reserva.setPrecioTotal(precioTotal);

        // Guardar la reserva en el mapa
        mapaReservas.put(idReserva, reserva);

        System.out.println("✅ Reserva creada con éxito: " + idReserva);
        return reserva;
      }
    }



    @Override
    public String toString() {
        return "Empleado{" +
                "puesto=" + puesto +
                ", fechaIngreso=" + fechaIngreso +
                "} " + super.toString();
    }
}
