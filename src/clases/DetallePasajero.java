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

    public DetallePasajero(Pasajero pasajero, Asiento asientoAsignado, List<Equipaje> listaEquipaje, double precioIndividual) {
        this.pasajero = pasajero;
        this.asientoAsignado = asientoAsignado;
        this.listaEquipaje = listaEquipaje;
        this.precioIndividual = precioIndividual;
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

    @Override
    public String toString() {
        return "DetallePasajero{" +
                "pasajero=" + pasajero +
                ", asientoAsignado=" + asientoAsignado +
                ", listaEquipaje=" + listaEquipaje +
                ", precioIndividual=" + precioIndividual +
                '}';
    }
}
