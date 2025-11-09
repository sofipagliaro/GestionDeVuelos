package clases;

import enums.EstadoAvion;
import enums.TamanioAvion;

import java.util.HashMap;

public class Avion {
    private String idAvion;
    private EstadoAvion estadoAvion;
    private TamanioAvion tamanioAvion;
    private HashMap<Integer, Asiento> mapaAsientos;

    /// CONSTRUCTOR
    public Avion() {
    }

    /// GETTERS Y SETTERS
    public String getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(String idAvion) {
        this.idAvion = idAvion;
    }

    public EstadoAvion getEstadoAvion() {
        return estadoAvion;
    }

    public void setEstadoAvion(EstadoAvion estadoAvion) {
        this.estadoAvion = estadoAvion;
    }

    public TamanioAvion getTamanioAvion() {
        return tamanioAvion;
    }

    public void setTamanioAvion(TamanioAvion tamanioAvion) {
        this.tamanioAvion = tamanioAvion;
    }

    public HashMap<Integer, Asiento> getMapaAsientos() {
        return mapaAsientos;
    }

    public void setMapaAsientos(HashMap<Integer, Asiento> mapaAsientos) {
        this.mapaAsientos = mapaAsientos;
    }
}
