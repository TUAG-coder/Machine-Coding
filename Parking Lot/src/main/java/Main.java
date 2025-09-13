import controllers.TicketController;
import enums.ParkingSpotStrategyType;
import factories.ParkingSpotAssignmentFactory;
import models.Ticket;
import models.dto.IssueTicketRequestDTO;
import models.dto.IssueTicketResponseDTO;
import repositories.GateRepository;
import repositories.VehicleRepository;
import services.TicketService;
import strategies.ParkingSpotAssignmentStrategy;

public class Main {
    public static void main(String[] args) {
        GateRepository gateRepository = new GateRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();

        ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy =
                ParkingSpotAssignmentFactory.getParkingSpotStrategy(ParkingSpotStrategyType.NEAREST);
        TicketService ticketService = new TicketService(gateRepository, vehicleRepository, parkingSpotAssignmentStrategy);

        TicketController ticketController = new TicketController(ticketService);
        IssueTicketRequestDTO requestDTO = new IssueTicketRequestDTO();
        requestDTO.setOwnerName("John");
        requestDTO.setVehicleNumber("XG76832LP");
        requestDTO.setGateId(5L);
        IssueTicketResponseDTO responseDTO = ticketController.issueTicket(requestDTO);
        Ticket ticket = responseDTO.getTicket();
    }
}
