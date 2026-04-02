package strategies;

import enums.DriverStatus;
import enums.VehicleType;
import models.Driver;
import models.Location;
import models.Passenger;
import repositories.DriverRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NearestDriverMatchingStrategy implements DriverMatchingStrategy {

    private DriverRepository driverRepository;

    public NearestDriverMatchingStrategy(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> match(Location source, VehicleType vehicleType) {

        Map<Long, Driver> drivers = driverRepository.getAllDrivers();
        List<Driver> result = new ArrayList<>();

        for (Driver driver : drivers.values()) {
            if (driver.getDriverStatus() != DriverStatus.ONLINE) continue;
            if (driver.getVehicle().getVehicleType() != vehicleType) continue;
            int distance = calculateDistance(source, driver.getCurrentLocation());
            if (distance <= 5) {
                result.add(driver);
            }
        }

        return result;
    }

    private int calculateDistance(Location a, Location b) {
        double lat = a.getLatitude() - b.getLatitude();
        double lon = a.getLongitude() - b.getLongitude();
        return (int) Math.sqrt(lat * lat + lon * lon);
    }
}
