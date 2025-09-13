package repositories;

import models.ElevatorCar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElevatorRepository {
    private Map<Long, ElevatorCar> elevatorCarMap = new HashMap<>();
    public List<ElevatorCar> findAllByBuilding(String buildingName) {
        List<ElevatorCar> elevatorCarList = new ArrayList<>();
        for (Long key: elevatorCarMap.keySet()) {
            ElevatorCar elevatorCar = elevatorCarMap.get(key);
            if (elevatorCar.getBuilding().equals(buildingName)) {
                elevatorCarList.add(elevatorCar);
            }
        }

        return elevatorCarList;
    }
}
