package factories;

import enums.ElevatorAssignmentStrategy;
import strategies.Dispatcher;
import strategies.ElevatorHeapStrategy;
import strategies.NearestElevatorStrategy;
import strategies.NearestPassingCarStrategy;

public class DispatcherFactory {
    public static Dispatcher selectElevatorAssignmentStrategy(ElevatorAssignmentStrategy strategy) {
        if (strategy.equals(ElevatorAssignmentStrategy.NEAREST_ELEVATOR_STRATEGY)) {
            return new NearestElevatorStrategy();
        } else if (strategy.equals(ElevatorAssignmentStrategy.NEAREST_PASSING_ELEVATOR_STRATEGY)) {
            return new NearestPassingCarStrategy();
        }
        return new ElevatorHeapStrategy();
    }
}
