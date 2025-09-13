package exceptions;

public class HallButtonNotFoundException extends RuntimeException{
    public HallButtonNotFoundException() {
        super();
    }
    public HallButtonNotFoundException(String message) {
        super(message);
    }
}
