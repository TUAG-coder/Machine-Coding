package models;

import enums.DriverStatus;

public class Driver extends BaseModel {
    private String name;
    private Vehicle vehicle;
    private Double rating;
    //transient
    private Location currentLocation;
    private DriverStatus driverStatus;

    // ------------ GETTERS & SETTERS -------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }


    // -------------- BUSINESS METHODS -------------
    public boolean isAvailable() {
        return this.driverStatus == DriverStatus.ONLINE;
    }

    public void assignRide() {
        if (this.driverStatus != DriverStatus.ONLINE) {
            throw new IllegalStateException("Driver not available");
        }
        this.driverStatus = DriverStatus.ON_TRIP;
    }

    public void completeRide() {
        this.driverStatus = DriverStatus.ONLINE;
    }

    public void goOffline() {
        this.driverStatus = DriverStatus.OFFLINE;
    }

    public void goOnline() {
        this.driverStatus = DriverStatus.ONLINE;
    }
}
