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
    private HashMap<Integer, Boolean> disponibilidadAsientos;
    private double precio;

    /// CONSTRUCTOR
    public Vuelo() {
    }

    /// GETTERS Y SETTERS
    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

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

    public HashMap<Integer, Boolean> getDisponibilidadAsientos() {
        return disponibilidadAsientos;
    }

    public void setDisponibilidadAsientos(HashMap<Integer, Boolean> disponibilidadAsientos) {
        this.disponibilidadAsientos = disponibilidadAsientos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
