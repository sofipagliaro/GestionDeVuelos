package clases;

import java.util.HashMap;

public class RepositorioGenerico <T>{

    private HashMap<String, T> elementos;

    public void GestorGenerico() {
        this.elementos = new HashMap<>();
    }

    // CREAR
    public void crear(String id, T elemento) {
        if (elementos.containsKey(id)) {
            System.out.println("Ya existe un elemento con el id: " + id);
        } else {
            elementos.put(id, elemento);
            System.out.println("Elemento agregado correctamente con id: " + id);
        }
    }

    // MODIFICAR
    public void modificar(String id, T elemento) {
        if (elementos.containsKey(id)) {
            elementos.put(id, elemento);
            System.out.println("Elemento con id " + id + " modificado correctamente.");
        } else {
            System.out.println("No se encontró un elemento con id: " + id);
        }
    }

    // ELIMINAR
    public void eliminar(String id) {
        if (elementos.containsKey(id)) {
            elementos.remove(id);
            System.out.println("Elemento con id " + id + " eliminado correctamente.");
        } else {
            System.out.println("No se encontró un elemento con id: " + id);
        }
    }

    // Obtener el mapa completo
    public HashMap<String, T> getElementos() {
        return elementos;
    }

    // OBTENER un elemento particular
    public T obtener(String id) {
        return elementos.get(id);
    }





}
