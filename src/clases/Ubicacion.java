package clases;

public class Ubicacion {
    private String ciudad;
    private String provincia;

    /// CONSTRUCTOR
    public Ubicacion() {
    }

    /// GETTERS Y SETTERS
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
