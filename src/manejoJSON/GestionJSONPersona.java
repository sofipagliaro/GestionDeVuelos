package manejoJSON;

import clases.*;
import enums.Puesto;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;

public class GestionJSONPersona {

    public static HashMap<String, Persona> mapeoPersonas() {
        HashMap<String, Persona> mapaPersonas = new HashMap<>();

        try {
            JSONObject jPersonas = new JSONObject(JSONUtiles.leer("personas.json"));
            Iterator<String> clave = jPersonas.keys();

            while (clave.hasNext()){
                String doc = clave.next();
                JSONObject jPersona = jPersonas.getJSONObject(doc);

                if (jPersona.getString("tipo").equals("EMPLEADO") ){
                    Empleado e = new Empleado();
                    e.setDni(jPersona.getString("dni"));
                    e.setNombre(jPersona.getString("nombre"));
                    e.setApellido(jPersona.getString("apellido"));
                    e.setDireccion(jPersona.getString("direccion"));
                    e.setTelefono(jPersona.getLong("telefono"));
                    e.setEmail(jPersona.getString("email"));

                    String strFechaNacimiento = jPersona.getString("fechaNacimiento");
                    LocalDate fechaNacimiento = LocalDate.parse(strFechaNacimiento);
                    e.setFechaNacimiento(fechaNacimiento);

                    e.setUsuario(jPersona.getString("usuario"));
                    e.setPassword(jPersona.getString("password"));

                    try {
                        String puestoString = jPersona.getString("puesto");
                        e.setPuesto(Puesto.valueOf(puestoString));
                    } catch (IllegalArgumentException ex){
                        throw new IllegalArgumentException("Ese puesto no pertenece a la lista");
                    }

                    String strFechaIngreso = jPersona.getString("fechaIngreso");
                    LocalDate fechaIngreso = LocalDate.parse(strFechaIngreso);
                    e.setFechaIngreso(fechaIngreso);

                    mapaPersonas.put(doc, e);
                }

                if (jPersona.getString("tipo").equals("ADMINISTRADOR") ){
                    Administrador a = new Administrador();
                    a.setDni(jPersona.getString("dni"));
                    a.setNombre(jPersona.getString("nombre"));
                    a.setApellido(jPersona.getString("apellido"));
                    a.setDireccion(jPersona.getString("direccion"));
                    a.setTelefono(jPersona.getLong("telefono"));
                    a.setEmail(jPersona.getString("email"));

                    String strFechaNacimiento = jPersona.getString("fechaNacimiento");
                    LocalDate fechaNacimiento = LocalDate.parse(strFechaNacimiento);
                    a.setFechaNacimiento(fechaNacimiento);

                    a.setUsuario(jPersona.getString("usuario"));
                    a.setPassword(jPersona.getString("password"));

                    try {
                        String puestoString = jPersona.getString("puesto");
                        a.setPuesto(Puesto.valueOf(puestoString));
                    } catch (IllegalArgumentException e){
                        throw new IllegalArgumentException("Ese puesto no pertenece a la lista");
                    }

                    String strFechaIngreso = jPersona.getString("fechaIngreso");
                    LocalDate fechaIngreso = LocalDate.parse(strFechaIngreso);
                    a.setFechaIngreso(fechaIngreso);

                    mapaPersonas.put(doc,a);
                }

                if (jPersona.getString("tipo").equals("PASAJERO") ){
                    Pasajero p = new Pasajero();
                    p.setDni(jPersona.getString("dni"));
                    p.setNombre(jPersona.getString("nombre"));
                    p.setApellido(jPersona.getString("apellido"));
                    p.setDireccion(jPersona.getString("direccion"));
                    p.setTelefono(jPersona.getLong("telefono"));
                    p.setEmail(jPersona.getString("email"));

                    String strFechaNacimiento = jPersona.getString("fechaNacimiento");
                    LocalDate fechaNacimiento = LocalDate.parse(strFechaNacimiento);
                    p.setFechaNacimiento(fechaNacimiento);

                    p.setUsuario(jPersona.getString("usuario"));
                    p.setPassword(jPersona.getString("password"));

                    mapaPersonas.put(doc,p);

                }

            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return mapaPersonas;
    }



}
