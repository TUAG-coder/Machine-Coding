package services;

import exceptions.GateNotFoundException;
import exceptions.VehicleNotFoundException;
import models.*;
import repositories.GateRepository;
import repositories.VehicleRepository;
import strategies.ParkingSpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy;

    public TicketService(GateRepository gateRepository, VehicleRepository vehicleRepository,
                         ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingSpotAssignmentStrategy = parkingSpotAssignmentStrategy;
    }

    public Ticket issueTicket(String vehicleNumber, String ownerName, Long gateId) {
        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        /*
        Gate gate = gateRepository.findGateById(gateId);
        int gateNumber = gate.getGateNumber(); // If gate is null, we'll get NullPointerException
        */

        Optional<Gate> optionalGate = gateRepository.findGateById(gateId);
        if (optionalGate.isEmpty()) {
            throw new GateNotFoundException("Gate not found for the given Id");
        }
        Gate gate = optionalGate.get(); //We were at least getting a warning before we wrote logic to check empty Gate

        ticket.setGate(gate);
        ticket.setOperator(gate.getOperator());

        Optional<Vehicle> optionalVehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        Vehicle vehicle = null;
        if (optionalVehicle.isEmpty()) {
            // It means that the Vehicle's info is not there in the DB, we'll create a Vehicle object and store the info.
            Vehicle vehicle1 = new Vehicle();
            vehicle1.setVehicleNumber(vehicleNumber);
            vehicle1.setOwnerName(ownerName);
            vehicle = vehicleRepository.save(vehicle1);
        } else {
            vehicle = optionalVehicle.get();
        }

        ticket.setVehicle(vehicle);

        ParkingSpot parkingSpot = parkingSpotAssignmentStrategy.assignParkingSpot(gate.getParkingLot(), vehicle);
        ticket.setParkingSpot(parkingSpot);

        return ticket;
    }
}
