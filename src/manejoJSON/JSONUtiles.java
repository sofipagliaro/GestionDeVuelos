package manejoJSON;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import clases.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONUtiles {

    public static <T> void guardarColeccion(Map<String, T> coleccion, String rutaArchivo) throws JSONException {

        JSONObject jsonPrincipal = new JSONObject();

        for (Map.Entry<String, T> entry : coleccion.entrySet()) {

            T objetoJava = entry.getValue();
            String clave = entry.getKey();
            JSONObject jsonObjeto = null;

            if (objetoJava instanceof Administrador) {
                Administrador admin = (Administrador) objetoJava;
                jsonObjeto = admin.toJSON();
                jsonObjeto.put("tipo", "ADMINISTRADOR");
            } else if (objetoJava instanceof Empleado) {
                Empleado empleado = (Empleado) objetoJava;
                jsonObjeto = empleado.toJSON();
                jsonObjeto.put("tipo", "EMPLEADO");
            } else if (objetoJava instanceof Pasajero) {
                Pasajero pasajero = (Pasajero) objetoJava;
                jsonObjeto = pasajero.toJSON();
                jsonObjeto.put("tipo", "PASAJERO");
            }

            else if (objetoJava instanceof Avion) {
                jsonObjeto = ((Avion) objetoJava).toJSON();
            } else if (objetoJava instanceof Vuelo) {
                jsonObjeto = ((Vuelo) objetoJava).toJSON();
            } else if (objetoJava instanceof Reserva) {
                jsonObjeto = ((Reserva) objetoJava).toJSON();
            } else if (objetoJava instanceof Aeropuerto) {
                jsonObjeto = ((Aeropuerto) objetoJava).toJSON();
            }

            if (jsonObjeto != null) {
                jsonPrincipal.put(clave, jsonObjeto);
            }
        }

        try (FileWriter file = new FileWriter(rutaArchivo)) {
            file.write(jsonPrincipal.toString(4));
            file.flush();
            System.out.println("Colección guardada exitosamente en: " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + rutaArchivo);
            e.printStackTrace();
        }
    }

    public static <T> void guardar(HashMap<String, T> mapa, String nombreArchivo) {
        try {
            JSONObject jObjetos = new JSONObject(mapa);

            FileWriter file = new FileWriter(nombreArchivo);
            file.write(jObjetos.toString(4));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
    }

    public static void grabar(JSONArray array) {
        try {
            FileWriter file = new FileWriter("");
            file.write(array.toString());
            file.flush();
            file.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    public static JSONTokener leer(String archivo) {
        JSONTokener tokener = null;

        try {
            tokener = new JSONTokener(new FileReader(archivo));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tokener;
    }
}
