package clases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();

        if (this.pasajero != null) {
            json.put("dniPasajero", this.pasajero.getDni());
        } else {
            json.put("dniPasajero", "ERROR_PASAJERO_NO_ASIGNADO");
        }

        if (this.asientoAsignado != null) {
            json.put("asientoAsignado", this.asientoAsignado.getIdAsiento());
        } else {
            json.put("asientoAsignado", JSONObject.NULL);
        }

        json.put("precioIndividual", this.precioIndividual);

        JSONArray jEquipajes = new JSONArray();
        if (this.listaEquipaje != null) {
            for (Equipaje e : this.listaEquipaje) {
                jEquipajes.put(e.toJSON());
            }
        }
        json.put("listaEquipaje", jEquipajes);

        return json;
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
