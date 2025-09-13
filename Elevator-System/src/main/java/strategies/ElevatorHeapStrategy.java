package strategies;

import enums.Direction;
import models.ElevatorCar;

import java.util.List;

public class ElevatorHeapStrategy implements Dispatcher{
    @Override
    public ElevatorCar assignElevator(List<ElevatorCar> elevatorCarList, int floor, Direction direction) {
        ElevatorCar best = null;
        int dist = Integer.MAX_VALUE;
        for (ElevatorCar elevatorCar: elevatorCarList) {
            int score = calculateScore(elevatorCar, floor, direction);
            if (dist > score) {
                best = elevatorCar;
                dist = score;
            }
        }
        return best;
    }

    private int calculateScore(ElevatorCar elevatorCar, int floor, Direction direction) {
        int score = Math.abs(elevatorCar.getCurrentFloor() - floor);

        if (elevatorCar.getDirection().equals(Direction.UP) &&
                direction.equals(Direction.UP) &&
                floor >= elevatorCar.getCurrentFloor()) {
            return score;
        } else if (elevatorCar.getDirection().equals(Direction.DOWN) &&
                direction.equals(Direction.DOWN) &&
                floor <= elevatorCar.getCurrentFloor()) {
            return score;
        }
        return score;
    }
}
