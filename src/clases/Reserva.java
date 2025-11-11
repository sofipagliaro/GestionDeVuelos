package clases;

import enums.MetodoDePago;

import java.time.LocalDateTime;
import java.util.List;

public class Reserva {
    private int idReserva;
    private Pasajero pasajero;
    private MetodoDePago metodoDePago;
    private LocalDateTime fechaHora;
    private Vuelo vuelo;
    private int cantidad;
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

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajeo) {
        this.pasajero = pasajero;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", pasajero=" + pasajero +
                ", metodoDePago=" + metodoDePago +
                ", fechaHora=" + fechaHora +
                ", vuelo=" + vuelo +
                ", cantidad=" + cantidad +
                ", detallePasajero=" + detallePasajero +
                ", precioTotal=" + precioTotal +
                '}';
    }
}
