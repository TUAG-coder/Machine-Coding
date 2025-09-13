package services;

import enums.Direction;
import exceptions.BuildingNotFoundException;
import exceptions.ElevatorNotFoundException;
import exceptions.HallButtonNotFoundException;
import models.Building;
import models.ElevatorCar;
import models.HallButton;
import repositories.BuildingRepository;
import repositories.ElevatorRepository;
import repositories.HallButtonRepository;
import strategies.Dispatcher;

import java.util.List;
import java.util.Optional;

public class HallButtonService {
    private BuildingRepository buildingRepository;
    private HallButtonRepository hallButtonRepository;

    private ElevatorRepository elevatorRepository;
    private Dispatcher dispatcher;

    public HallButtonService(BuildingRepository buildingRepository,
                             HallButtonRepository hallButtonRepository,
                             ElevatorRepository elevatorRepository,
                             Dispatcher dispatcher) {
        this.buildingRepository = buildingRepository;
        this.hallButtonRepository = hallButtonRepository;
        this.elevatorRepository = elevatorRepository;
        this.dispatcher = dispatcher;
    }
    public void handleHallCall(String buildingName, int floor, Direction direction) {
        Optional<Building> optionalBuilding = this.buildingRepository.findBuildingByName(buildingName);
        if (optionalBuilding.isEmpty()) {
            throw new BuildingNotFoundException("Building not found for the given name");
        }
        Building building = optionalBuilding.get();

        Optional<HallButton> optionalHallButton =
                this.hallButtonRepository.findByBuildingAndFloorAndDirection(buildingName, floor, direction);
        if (optionalHallButton.isEmpty()) {
            throw new HallButtonNotFoundException("Hall button is not found for the given floor and direction");
        }
        HallButton hallButton = optionalHallButton.get();

        hallButton.setPressed(Boolean.TRUE);

        List<ElevatorCar> elevatorCarList = this.elevatorRepository.findAllByBuilding(buildingName);

        if (elevatorCarList.isEmpty()) {
            throw new ElevatorNotFoundException("No elevators present in the given building");
        }

        ElevatorCar chosenCar = this.dispatcher.assignElevator(elevatorCarList, floor, direction);
    }
}
