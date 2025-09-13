package factories;

import enums.ParkingSpotStrategyType;
import strategies.CheapestParkingSpotAssignmentStrategy;
import strategies.NearestParkingSpotAssignmentStrategy;
import strategies.ParkingSpotAssignmentStrategy;

// Practical Factory
public class ParkingSpotAssignmentFactory {

    // Factory method
    public static ParkingSpotAssignmentStrategy getParkingSpotStrategy(ParkingSpotStrategyType type) {
        if (type == ParkingSpotStrategyType.NEAREST) {
            return new NearestParkingSpotAssignmentStrategy();
        } else if (type == ParkingSpotStrategyType.CHEAPEST) {
            return new CheapestParkingSpotAssignmentStrategy();
        }
        return null;
    }
}
