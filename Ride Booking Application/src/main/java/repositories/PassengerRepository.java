package repositories;

import models.Passenger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PassengerRepository {
    private Map<Long, Passenger> passengerMap = new HashMap<>();

    public Optional<Passenger> findById(Long passengerId) {
        if (passengerMap.containsKey(passengerId)) {
            return Optional.of(passengerMap.get(passengerId));
        }
        return Optional.empty();
    }
}
