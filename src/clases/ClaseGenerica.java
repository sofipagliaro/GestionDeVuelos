package clases;

import excepciones.IdDuplicadoException;
import manejoJSON.JSONUtiles;

import java.util.HashMap;

public class ClaseGenerica<T> {
    private final HashMap<String, T> mapaEntidades;
    private final String nombreArchivo;

    public ClaseGenerica(String nombreArchivo, HashMap<String, T> mapaInicial) {
        this.nombreArchivo = nombreArchivo;
        this.mapaEntidades = new HashMap<>(mapaInicial);
    }

    public void agregar(String clave, T objeto) {
        if (mapaEntidades.containsKey(clave)) {
            throw new IdDuplicadoException("La clave "+ clave + " ya se encuentra registrada, por lo que no es posible ingresarla como nueva.");
        }

        mapaEntidades.put(clave, objeto);

        guardarJSON();

    }

    private void guardarJSON() {
        JSONUtiles.guardar(mapaEntidades, nombreArchivo);
        System.out.println("Archivo " + nombreArchivo + " actualizado con " + mapaEntidades.size() + " entidades.");
    }

    public HashMap<String, T> getMapaEntidades() {
        return mapaEntidades;
    }
}
