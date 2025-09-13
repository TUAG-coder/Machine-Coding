package strategies;

import enums.Direction;
import models.ElevatorCar;

import java.util.List;

public class NearestElevatorStrategy implements Dispatcher{
    @Override
    public ElevatorCar assignElevator(List<ElevatorCar> elevatorCarList, int floor, Direction direction) {
        ElevatorCar best = null;
        int maxDistance = Integer.MAX_VALUE;
        for (ElevatorCar elevatorCar: elevatorCarList) {
            int dist = Math.abs(elevatorCar.getCurrentFloor() - floor);
            if (dist < maxDistance) {
                maxDistance = dist;
                best = elevatorCar;
            }
        }
        return best;
    }
}