package models;

import java.util.List;

// @Entity
public class Building extends BaseModel{
    private String name;
    private List<Floor> floors;
    private List<ElevatorCar> elevatorCarList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public List<ElevatorCar> getElevatorCarList() {
        return elevatorCarList;
    }

    public void setElevatorCarList(List<ElevatorCar> elevatorCarList) {
        this.elevatorCarList = elevatorCarList;
    }
}
