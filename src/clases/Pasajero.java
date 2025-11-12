package clases;

import interfaces.I_VerViajes;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class Pasajero extends Persona {

    public Pasajero() {
    }

    public Pasajero(String dni, String nombre, String apellido, String direccion, long telefono, String email, LocalDate fechaNacimiento, String usuario, String password) {
        super(dni, nombre, apellido, direccion, telefono, email, fechaNacimiento, usuario, password);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();

        json.put("dni", this.getDni());
        json.put("nombre", this.getNombre());
        json.put("apellido", this.getApellido());
        json.put("direccion", this.getDireccion());
        json.put("telefono", this.getTelefono());
        json.put("email", this.getEmail());
        json.put("fechaNacimiento", this.getFechaNacimiento().toString());
        json.put("usuario", this.getUsuario());
        json.put("password", this.getPassword());

        return json;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
