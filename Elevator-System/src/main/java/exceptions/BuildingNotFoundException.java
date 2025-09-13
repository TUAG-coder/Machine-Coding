package exceptions;

public class BuildingNotFoundException extends RuntimeException{
    public BuildingNotFoundException() {
        super();
    }
    public BuildingNotFoundException(String message) {
        super(message);
    }
}
