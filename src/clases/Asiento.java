package clases;

import enums.TipoClase;

public class Asiento {
    private int idAsiento;
    private TipoClase tipoClase;
    private double precio;

    /// CONSTRUCTOR
    public Asiento() {
    }

    /// GETTERS Y SETTERS
    public int getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public TipoClase getTipoClase() {
        return tipoClase;
    }

    public void setTipoClase(TipoClase tipoClase) {
        this.tipoClase = tipoClase;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Asiento{" +
                "idAsiento=" + idAsiento +
                ", tipoClase=" + tipoClase +
                ", precio=" + precio +
                '}';
    }
}


