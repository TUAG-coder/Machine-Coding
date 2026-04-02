package repositories;

import models.Booking;
import models.Driver;
import models.Passenger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DriverRepository {
    private Map<Long, Driver> driverMap = new HashMap<>();

    public Map<Long, Driver> getAllDrivers() {
        return this.driverMap;
    }

    public Optional<Driver> findById(Long id) {
        if (driverMap.containsKey(id)) {
            return Optional.of(driverMap.get(id));
        }
        return Optional.empty();
    }
}
