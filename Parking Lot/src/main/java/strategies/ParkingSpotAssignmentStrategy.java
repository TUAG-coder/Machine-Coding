package strategies;

import models.ParkingLot;
import models.ParkingSpot;
import models.Vehicle;

public interface ParkingSpotAssignmentStrategy {
    ParkingSpot assignParkingSpot(ParkingLot parkingLot, Vehicle vehicle);
}
