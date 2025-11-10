package clases;

import java.util.HashMap;

public class RepositorioGenerico <T>{

    private HashMap<String, T> elementos;

    public void GestorGenerico() {
        this.elementos = new HashMap<>();
    }

}
