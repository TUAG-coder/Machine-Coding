package exceptions;

public class GateNotFoundException extends RuntimeException{
    public GateNotFoundException() {
        super();
    }
    public GateNotFoundException(String message) {
        super(message);
    }
}
