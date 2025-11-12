package clases;

import enums.Puesto;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class Administrador extends Empleado {

    public Administrador() {}

    public Administrador(String dni, String nombre, String apellido, String direccion, long telefono, String email, LocalDate fechaNacimiento, String usuario, String password, Puesto puesto, LocalDate fechaIngreso) {
        super(dni, nombre, apellido, direccion, telefono, email, fechaNacimiento, usuario, password, puesto, fechaIngreso);
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject json = super.toJSON();

        json.put("puesto", this.getPuesto().name());
        json.put("fechaIngreso", this.getFechaIngreso().toString());

        return json;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
