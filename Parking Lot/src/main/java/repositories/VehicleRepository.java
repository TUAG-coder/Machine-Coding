package repositories;

import models.Vehicle;

import java.util.Optional;

public class VehicleRepository {

    // Output Vehicle object will have id present (Same as Database)
    public Vehicle save(Vehicle vehicle) {
        return null;
    }
    public Optional<Vehicle> findByVehicleNumber(String vehicleNumber) {
        return Optional.empty();
    }

    public Optional<Vehicle> findById(Long id) {
        return Optional.empty();
    }
}
