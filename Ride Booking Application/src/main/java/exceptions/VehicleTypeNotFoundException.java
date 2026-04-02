package exceptions;

public class VehicleTypeNotFoundException extends Exception{
    private String message;
    public VehicleTypeNotFoundException(String message) {
        this.message = message;
    }
}
