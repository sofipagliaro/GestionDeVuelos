package clases;

import enums.MetodoDePago;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public Reserva(int idReserva, Pasajero pasajero, MetodoDePago metodoDePago, LocalDateTime fechaHora, Vuelo vuelo, int cantidad, List<DetallePasajero> detallePasajero, double precioTotal) {
        this.idReserva = idReserva;
        this.pasajero = pasajero;
        this.metodoDePago = metodoDePago;
        this.fechaHora = fechaHora;
        this.vuelo = vuelo;
        this.cantidad = cantidad;
        this.detallePasajero = detallePasajero;
        this.precioTotal = precioTotal;
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


    public boolean contienePasajero(Pasajero pasajero) {
        if (this.detallePasajero == null || pasajero == null) {
            return false;
        }

        for (DetallePasajero detalle : this.detallePasajero) {
            Pasajero pasajeroDelDetalle = detalle.getPasajero();
            if (pasajeroDelDetalle != null && pasajeroDelDetalle.getDni().equals(pasajero.getDni())) {
                return true;
            }
        }
        return false;
    }


    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();

        json.put("idReserva", this.idReserva);

        json.put("idVuelo", this.vuelo.getIdVuelo());

        json.put("dniPasajeroTitular", this.pasajero.getDni());

        json.put("metodoDePago", this.metodoDePago.name());
        json.put("fechaHora", this.fechaHora.toString()); // Formato ISO 8601
        json.put("cantidad", this.cantidad);
        json.put("precioTotal", this.precioTotal);


        JSONArray jDetalles = new JSONArray();
        for (DetallePasajero dp : this.detallePasajero) {
            jDetalles.put(dp.toJSON()); // Llama al toJSON de DetallePasajero
        }
        json.put("detallePasajero", jDetalles);

        return json;
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
