package exceptions;

public class BookingNotFoundException extends Exception{
    private String message;
    public BookingNotFoundException(String message) {
        this.message = message;
    }
}
