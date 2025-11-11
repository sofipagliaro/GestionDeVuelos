package excepciones;

public class AeropuertoEnUsoException extends RuntimeException {
    public AeropuertoEnUsoException(String message) {
        super(message);
    }
}
