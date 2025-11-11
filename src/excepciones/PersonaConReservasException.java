package excepciones;

public class PersonaConReservasException extends RuntimeException {
    public PersonaConReservasException(String message) {
        super(message);
    }
}
