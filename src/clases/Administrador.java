package clases;

import interfaces.I_VerViajes;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Administrador extends Empleado implements I_VerViajes {



    private List<Vuelo> vuelosGestionados;
    private List<Reserva> reservasRegistradas;

    public Administrador() {
        this.vuelosGestionados = new ArrayList<>();
        this.reservasRegistradas = new ArrayList<>();
    }
    public List<Vuelo> getVuelosGestionados() {
        return vuelosGestionados;
    }

    public List<Reserva> getReservasRegistradas() {
        return reservasRegistradas;
    }

    public void crearVuelo(HashMap<String, Vuelo> mapaVuelos, Vuelo vueloNuevo) {
        if (mapaVuelos.containsKey(vueloNuevo.getIdVuelo())) {
            System.out.println("Ya existe un vuelo con ese ID: " + vueloNuevo.getIdVuelo());
        } else {
            mapaVuelos.put(vueloNuevo.getIdVuelo(), vueloNuevo);
            System.out.println("Vuelo creado correctamente: " + vueloNuevo.getIdVuelo());
        }
    }

    public void eliminarVuelo(HashMap<String, Vuelo> mapaVuelos, String idVuelo) {
        if (mapaVuelos.remove(idVuelo) != null) {
            System.out.println("Vuelo eliminado correctamente: " + idVuelo);
        } else {
            System.out.println("No se encontró el vuelo con ID: " + idVuelo);
        }
    }

    public void crearEmpleado(HashMap<String, Empleado> mapaEmpleados, Empleado empleadoNuevo) {
        if (mapaEmpleados.containsKey(empleadoNuevo.getDni())) {
            System.out.println("Ya existe un empleado con ese DNI: " + empleadoNuevo.getDni());
        } else {
            mapaEmpleados.put(empleadoNuevo.getDni(), empleadoNuevo);
            System.out.println("Empleado creado correctamente: " + empleadoNuevo.getNombre());
        }
    }

    public void eliminarEmpleado(HashMap<String, Empleado> mapaEmpleados, String dniEmpleado) {
        if (mapaEmpleados.remove(dniEmpleado) != null) {
            System.out.println("Empleado eliminado correctamente: " + dniEmpleado);
        } else {
            System.out.println("No se encontró el empleado con DNI: " + dniEmpleado);
        }
    }


    @Override
    public
    List<Vuelo> verVuelos() {
        return vuelosGestionados;
    }

    @Override
    public List<Reserva> verReservas() {
        return reservasRegistradas;
    }

}
