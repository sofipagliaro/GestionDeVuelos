package clases;

import org.json.JSONException;
import org.json.JSONObject;

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

    public Vuelo(String idVuelo, Aeropuerto origen, Aeropuerto destino, LocalDateTime fechaHora, int duracion, Avion avion, HashMap<Integer, Boolean> asientosReservados, double precio) {
        this.idVuelo = idVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fechaHora = fechaHora;
        this.duracion = duracion;
        this.avion = avion;

        if (asientosReservados == null) {
            this.asientosReservados = new HashMap<>(); // ¡Inicialmente vacío!
        } else {
            this.asientosReservados = asientosReservados;
        }

        this.precio = precio;
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

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();

        json.put("idVuelo", this.idVuelo);

        if (this.origen != null) {
            json.put("idOrigen", this.origen.getCodigo());
        }
        if (this.destino != null) {
            json.put("idDestino", this.destino.getCodigo());
        }

        json.put("fechaHora", this.fechaHora.toString());
        json.put("duracion", this.duracion);

        if (this.avion != null) {
            json.put("idAvion", this.avion.getIdAvion());
        }

        json.put("asientosReservados", new JSONObject(this.asientosReservados));

        json.put("precio", this.precio);

        return json;
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


    public static int calcularTotalPasajeros(HashMap<String, Vuelo> mapaVuelos) {
        int totalPasajeros = 0;

        for (Vuelo vuelo : mapaVuelos.values()) {

            HashMap<Integer, Boolean> asientosReservados = vuelo.getAsientosReservados();

            for (Boolean estaReservado : asientosReservados.values()) {

                if (estaReservado) {
                    totalPasajeros++;
                }
            }
        }

        return totalPasajeros;
    }


}
