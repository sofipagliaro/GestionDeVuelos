package clases;

import enums.TipoEquipaje;

public class Equipaje {
    private TipoEquipaje tipoEquipaje;
    private double precio;
    private int cantidad;

    /// CONSTRUCTOR
    public Equipaje() {
    }

    public Equipaje(TipoEquipaje tipoEquipaje, double precio, int cantidad) {
        this.tipoEquipaje = tipoEquipaje;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    /// GETTERS Y SETTERS
    public TipoEquipaje getTipoEquipaje() {
        return tipoEquipaje;
    }

    public void setTipoEquipaje(TipoEquipaje tipoEquipaje) {
        this.tipoEquipaje = tipoEquipaje;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Equipaje{" +
                "tipoEquipaje=" + tipoEquipaje +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                '}';
    }
}
