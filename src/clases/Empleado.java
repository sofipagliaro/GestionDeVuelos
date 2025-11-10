package clases;

import enums.Puesto;

import java.time.LocalDate;

public class Empleado extends Usuario{
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



    @Override
    public String toString() {
        return "Empleado{" +
                "puesto=" + puesto +
                ", fechaIngreso=" + fechaIngreso +
                "} " + super.toString();
    }
}
