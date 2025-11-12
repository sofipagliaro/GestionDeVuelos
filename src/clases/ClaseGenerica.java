package clases;

import excepciones.IdDuplicadoException;
import excepciones.IdNoExistenteException;
import manejoJSON.JSONUtiles;
import org.json.JSONException;

import java.util.HashMap;

public class ClaseGenerica<T> {
    private final HashMap<String, T> mapaEntidades;
    private final String nombreArchivo;

    public ClaseGenerica(String nombreArchivo, HashMap<String, T> mapaInicial) {
        this.nombreArchivo = nombreArchivo;
        this.mapaEntidades = new HashMap<>(mapaInicial);
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void agregar(String clave, T objeto) {
        if (mapaEntidades.containsKey(clave)) {
            throw new IdDuplicadoException("La clave "+ clave + " ya se encuentra registrada, por lo que no es posible ingresarla como nueva.");
        }
        mapaEntidades.put(clave, objeto);
        guardarJSON();
    }

    public T leer(String clave) throws IdNoExistenteException {
        if (!mapaEntidades.containsKey(clave)) {
            throw new IdNoExistenteException("La entidad con clave '" + clave + "' no fue encontrada.");
        }
        return mapaEntidades.get(clave);
    }

    public void actualizar(String clave, T objeto) {
        if (!mapaEntidades.containsKey(clave)) {
            throw new IdNoExistenteException("La entidad con clave '" + clave + "' no puede ser actualizada porque no existe.");
        }
        mapaEntidades.put(clave, objeto);
        guardarJSON();
    }

    public void eliminar(String clave) {
        if (!mapaEntidades.containsKey(clave)) {
            throw new IdNoExistenteException("La entidad con clave '" + clave + "' no puede ser eliminada porque no existe.");
        }

        mapaEntidades.remove(clave);
        guardarJSON();
    }

    private void guardarJSON() {
        try {
            JSONUtiles.guardarColeccion(mapaEntidades, nombreArchivo);
            System.out.println("Archivo " + nombreArchivo + " actualizado con " + mapaEntidades.size() + " entidades.");
        } catch (JSONException e) {
            System.err.println("ERROR: No se pudo guardar la colección debido a un problema de formato JSON.");
            e.printStackTrace();
        }
    }

    public HashMap<String, T> getMapaEntidades() {
        return mapaEntidades;
    }
}
