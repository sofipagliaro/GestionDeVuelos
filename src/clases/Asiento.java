package clases;

import enums.TipoClase;
import org.json.JSONException;
import org.json.JSONObject;

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

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("idAsiento", this.idAsiento);
        // Convertir Enum a String (ej: "TURISTA")
        json.put("tipoClase", this.tipoClase.name());
        json.put("precio", this.precio);
        return json;
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


