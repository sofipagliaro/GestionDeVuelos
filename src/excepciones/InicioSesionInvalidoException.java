package excepciones;

public class InicioSesionInvalidoException extends RuntimeException {
    public InicioSesionInvalidoException(String message) {
        super(message);
    }
}
