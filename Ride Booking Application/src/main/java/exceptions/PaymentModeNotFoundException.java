package exceptions;

public class PaymentModeNotFoundException extends Exception{
    private String message;
    public PaymentModeNotFoundException(String message) {
        this.message = message;
    }
}
