package models;

import enums.ParkingSpotStatus;
import enums.VehicleType;

import java.util.List;

public class ParkingSpot extends BaseModel {
    private String spotNumber;
    private ParkingSpotStatus spotStatus;
    private List<VehicleType> supportedVehicleTypes;

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public ParkingSpotStatus getSpotStatus() {
        return spotStatus;
    }

    public void setSpotStatus(ParkingSpotStatus spotStatus) {
        this.spotStatus = spotStatus;
    }

    public List<VehicleType> getSupportedVehicleTypes() {
        return supportedVehicleTypes;
    }

    public void setSupportedVehicleTypes(List<VehicleType> supportedVehicleTypes) {
        this.supportedVehicleTypes = supportedVehicleTypes;
    }
}
