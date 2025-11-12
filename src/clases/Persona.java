package clases;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public abstract class Persona {
    private String dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private long telefono;
    private String email;
    private LocalDate fechaNacimiento;
    private String usuario;
    private String password;

    /// CONSTRUCTOR
    public Persona() {
    }

    public Persona(String dni, String nombre, String apellido, String direccion, long telefono, String email, LocalDate fechaNacimiento, String usuario, String password) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.usuario = usuario;
        this.password = password;
    }

    /// GETTERS Y SETTERS
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }


    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();

        json.put("dni", this.dni);
        json.put("nombre", this.nombre);
        json.put("apellido", this.apellido);
        json.put("direccion", this.direccion);
        json.put("telefono", this.telefono);
        json.put("email", this.email);
        json.put("fechaNacimiento", this.fechaNacimiento.toString());
        json.put("usuario", this.usuario);
        json.put("password", this.password);

        return json;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono=" + telefono +
                ", email='" + email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
