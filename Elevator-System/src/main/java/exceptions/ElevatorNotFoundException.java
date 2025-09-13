package exceptions;

import repositories.ElevatorRepository;

public class ElevatorNotFoundException extends RuntimeException{
    public ElevatorNotFoundException() {
        super();
    }

    public ElevatorNotFoundException(String message) {
        super(message);
    }
}
