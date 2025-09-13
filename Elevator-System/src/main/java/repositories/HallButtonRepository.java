package repositories;

import enums.Direction;
import models.Building;
import models.Floor;
import models.HallButton;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HallButtonRepository {
    private Map<Long, HallButton> hallButtonMap = new HashMap<>();

    private void addHallButton() {
        HallButton hallButton = new HallButton();
        hallButton.setId(1L);
        hallButton.setDirection(Direction.DOWN);
        Floor floor = new Floor();
        floor.setFloorNumber(3);
        hallButton.setFloor(floor);
        hallButton.setBuilding(new Building());
        hallButtonMap.put(hallButton.getId(), hallButton);
    }

    public Optional<HallButton> findByBuildingAndFloorAndDirection(String buildingName, int floor, Direction direction) {
        for (Long key: hallButtonMap.keySet()) {
            HallButton hallButton = hallButtonMap.get(key);
            if (hallButton.getBuilding().getName().equals(buildingName) &&
                    hallButton.getFloor().getFloorNumber() == floor &&
                    hallButton.getDirection().equals(direction)) {
                return Optional.of(hallButton);
            }
        }
        return Optional.empty();
    }
}
