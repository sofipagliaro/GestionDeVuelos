package clases;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Vuelo {
    private String idVuelo;
    private Aeropuerto origen;
    private Aeropuerto destino;
    private LocalDateTime fechaHora;
    private int duracion;
    private Avion avion;
    private HashMap<Integer, Boolean> asientosReservados;
    private double precio;

    /// CONSTRUCTOR
    public Vuelo() {
    }

    public String getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(String idVuelo) {
        this.idVuelo = idVuelo;
    }

    /// GETTERS Y SETTERS
    public Aeropuerto getOrigen() {
        return origen;
    }

    public void setOrigen(Aeropuerto origen) {
        this.origen = origen;
    }

    public Aeropuerto getDestino() {
        return destino;
    }

    public void setDestino(Aeropuerto destino) {
        this.destino = destino;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public HashMap<Integer, Boolean> getAsientosReservados() {
        return asientosReservados;
    }

    public void setAsientosReservados(HashMap<Integer, Boolean> asientosReservados) {
        this.asientosReservados = asientosReservados;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "idVuelo='" + idVuelo + '\'' +
                ", origen=" + origen +
                ", destino=" + destino +
                ", fechaHora=" + fechaHora +
                ", duracion=" + duracion +
                ", avion=" + avion +
                ", asientosReservados=" + asientosReservados +
                ", precio=" + precio +
                '}';
    }
}
