package excepciones;

public class IdNoExistenteException extends RuntimeException {
    public IdNoExistenteException(String message) {
        super(message);
    }
}
