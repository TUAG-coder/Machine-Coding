package exceptions;

public class PassengerNotFoundException extends Exception{
    private String message;
    public PassengerNotFoundException(String message) {
        this.message = message;
    }
}
