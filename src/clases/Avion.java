package clases;

import enums.EstadoAvion;
import enums.TamanioAvion;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("idAvion", this.idAvion);
        json.put("estadoAvion", this.estadoAvion.name());
        json.put("tamanioAvion", this.tamanioAvion.name());

        JSONObject jMapaAsientos = new JSONObject();

        for (Map.Entry<Integer, Asiento> entry : this.mapaAsientos.entrySet()) {
            String idAsientoString = String.valueOf(entry.getKey());
            jMapaAsientos.put(idAsientoString, entry.getValue().toJSON());
        }

        json.put("mapaAsientos", jMapaAsientos);

        return json;
    }

    @Override
    public String toString() {
        return "Avion{" +
                "idAvion='" + idAvion + '\'' +
                ", estadoAvion=" + estadoAvion +
                ", tamanioAvion=" + tamanioAvion +
                ", mapaAsientos=" + mapaAsientos +
                '}';
    }
}
