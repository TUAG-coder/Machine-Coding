import controllers.HallButtonController;
import enums.Direction;
import enums.ElevatorAssignmentStrategy;
import factories.DispatcherFactory;
import models.dtos.HallButtonPressedRequestDto;
import models.dtos.HallButtonPressedResponseDto;
import repositories.BuildingRepository;
import repositories.ElevatorRepository;
import repositories.HallButtonRepository;
import services.HallButtonService;
import strategies.Dispatcher;

public class Main {
    public static void main(String[] args) {
        BuildingRepository buildingRepository = new BuildingRepository();
        ElevatorRepository elevatorRepository = new ElevatorRepository();
        HallButtonRepository hallButtonRepository = new HallButtonRepository();

        Dispatcher dispatcher = DispatcherFactory
                .selectElevatorAssignmentStrategy(ElevatorAssignmentStrategy.ELEVATOR_HEAP_STRATEGY);

        HallButtonService hallButtonService = new HallButtonService(
                buildingRepository,
                hallButtonRepository,
                elevatorRepository,
                dispatcher);

        HallButtonController hallButtonController = new HallButtonController(hallButtonService);

        HallButtonPressedRequestDto requestDto = new HallButtonPressedRequestDto();
        requestDto.setBuilding("Alpine Heights");
        requestDto.setFloor(10);
        requestDto.setDirection(Direction.DOWN);

        HallButtonPressedResponseDto responseDto =
                hallButtonController.pressHallButton(requestDto);
    }
}
