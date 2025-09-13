package strategies;

import enums.Direction;
import models.ElevatorCar;

import java.util.List;

public class NearestPassingCarStrategy implements Dispatcher{
    @Override
    public ElevatorCar assignElevator(List<ElevatorCar> elevatorCarList, int floor, Direction direction) {
        ElevatorCar best = null;
        int maxDistance = Integer.MAX_VALUE;
        for (ElevatorCar elevatorCar: elevatorCarList) {
            int dist = calculateScore(floor, direction, elevatorCar);
            if (dist < maxDistance) {
                maxDistance = dist;
                best = elevatorCar;
            }
        }
        return best;
    }

    private int calculateScore(int floor, Direction direction, ElevatorCar elevatorCar) {
        int score = Math.abs(elevatorCar.getCurrentFloor() - floor);

        return switch (elevatorCar.getStatus()) {
            case IDLE -> score;
            case MOVING -> {
                if (elevatorCar.getDirection().equals(Direction.UP) &&
                        direction.equals(Direction.UP) &&
                        floor >= elevatorCar.getCurrentFloor()) {
                    yield score;
                } else if (elevatorCar.getDirection().equals(Direction.DOWN) &&
                        direction.equals(Direction.DOWN) &&
                        floor <= elevatorCar.getCurrentFloor()) {
                    yield score;
                }
                yield score + 100;
            }
            case OUT_OF_SERVICE -> Integer.MAX_VALUE;
            default -> Integer.MAX_VALUE;
        };
    }
}