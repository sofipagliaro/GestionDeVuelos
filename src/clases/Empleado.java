package clases;

import enums.Puesto;

import java.time.LocalDate;

public class Empleado extends Persona{
    private Puesto puesto;
    private LocalDate fechaIngreso;

    /// CONSTRUCTOR
    public Empleado() {
    }

    /// GETTERS Y SETTERS
    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
