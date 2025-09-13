package repositories;

import models.Building;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BuildingRepository {
    private Map<String, Building> buildingMap = new HashMap<>();
    public Optional<Building> findBuildingByName(String name) {
        return Optional.of(buildingMap.get(name));
    }
}
