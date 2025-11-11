package excepciones;

public class ReservaNoPermitidaException extends RuntimeException {
    public ReservaNoPermitidaException(String message) {
        super(message);
    }
}
