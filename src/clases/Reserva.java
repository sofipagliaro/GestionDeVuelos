package clases;

import enums.MetodoDePago;

import java.time.LocalDateTime;
import java.util.List;

public class Reserva {
    private int idReserva;
    private Pasajero cliente;
    private MetodoDePago metodoDePago;
    private LocalDateTime fechaHora;
    private Vuelo vuelo;
    private List<DetallePasajero> detallePasajero;
    private double precioTotal;

    /// CONSTRUCTOR
    public Reserva() {
    }

    /// GETTERS Y SETTERS
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Pasajero getCliente() {
        return cliente;
    }

    public void setCliente(Pasajero cliente) {
        this.cliente = cliente;
    }

    public MetodoDePago getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(MetodoDePago metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public List<DetallePasajero> getDetallePasajero() {
        return detallePasajero;
    }

    public void setDetallePasajero(List<DetallePasajero> detallePasajero) {
        this.detallePasajero = detallePasajero;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
}
