package manejoJSON;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONUtiles {

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
