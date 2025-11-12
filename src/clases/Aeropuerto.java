package clases;

import org.json.JSONException;
import org.json.JSONObject;

public class Aeropuerto {
    private String codigo;
    private String nombre;
    private Ubicacion ubicacion;

    /// CONSTRUCTOR
    public Aeropuerto() {
    }

    /// GETTERS Y SETTERS
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("codigo", this.codigo);
        json.put("nombre", this.nombre);

        json.put("ubicacion", this.ubicacion.toJSON());

        return json;
    }

    @Override
    public String toString() {
        return "Aeropuerto{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", ubicacion=" + ubicacion +
                '}';
    }
}
