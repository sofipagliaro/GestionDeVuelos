package excepciones;

public class AvionEnUsoException extends RuntimeException {
    public AvionEnUsoException(String message) {
        super(message);
    }
}
