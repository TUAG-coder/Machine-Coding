package strategies;

import enums.VehicleType;
import models.Driver;
import models.Location;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> match(Location source, VehicleType vehicleType);
}
