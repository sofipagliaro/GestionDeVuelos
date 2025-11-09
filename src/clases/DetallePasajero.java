package clases;

import java.util.List;

public class DetallePasajero {
    private Pasajero pasajero;
    private Asiento asientoAsignado;
    private List<Equipaje> listaEquipaje;
    private double precioIndividual;

    /// CONSTRUCTOR
    public DetallePasajero() {
    }

    /// GETTERS Y SETTERS
    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Asiento getAsientoAsignado() {
        return asientoAsignado;
    }

    public void setAsientoAsignado(Asiento asientoAsignado) {
        this.asientoAsignado = asientoAsignado;
    }

    public List<Equipaje> getListaEquipaje() {
        return listaEquipaje;
    }

    public void setListaEquipaje(List<Equipaje> listaEquipaje) {
        this.listaEquipaje = listaEquipaje;
    }

    public double getPrecioIndividual() {
        return precioIndividual;
    }

    public void setPrecioIndividual(double precioIndividual) {
        this.precioIndividual = precioIndividual;
    }
}
