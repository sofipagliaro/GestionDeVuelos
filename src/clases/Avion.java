package clases;

import enums.EstadoAvion;

import java.util.List;

public class Avion {
    private String idAvion;
    private EstadoAvion estadoAvion;
    private List<Asiento> listaAsientos;

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

    public List<Asiento> getListaAsientos() {
        return listaAsientos;
    }

    public void setListaAsientos(List<Asiento> listaAsientos) {
        this.listaAsientos = listaAsientos;
    }
}
