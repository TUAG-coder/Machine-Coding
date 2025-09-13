package strategies;

import enums.Direction;
import models.ElevatorCar;

import java.util.List;

public interface Dispatcher {
    ElevatorCar assignElevator(List<ElevatorCar> elevatorCarList, int floor, Direction direction);
}
