package clases;

import enums.MetodoDePago;
import enums.Puesto;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Empleado extends Persona {
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
