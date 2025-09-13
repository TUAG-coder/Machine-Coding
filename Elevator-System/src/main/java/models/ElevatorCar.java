package models;

import enums.Direction;
import enums.ElevatorStatus;

import java.util.Comparator;
import java.util.PriorityQueue;

// @Entity
public class ElevatorCar extends BaseModel{
    private CarButtonPanel carButtonPanel;
    private Integer maxCapacity;

    private Building building;

    // Transient
    private ElevatorStatus status;

    //Transient
    private Direction direction;

    // Transient
    private int currentFloor;

    private PriorityQueue<Integer> upStops = new PriorityQueue<>();

    private PriorityQueue<Integer> downStops = new PriorityQueue<>(Comparator.reverseOrder());

    public CarButtonPanel getCarButtonPanel() {
        return carButtonPanel;
    }

    public void setCarButtonPanel(CarButtonPanel carButtonPanel) {
        this.carButtonPanel = carButtonPanel;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public ElevatorStatus getStatus() {
        return status;
    }

    public void setStatus(ElevatorStatus status) {
        this.status = status;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public PriorityQueue<Integer> getUpStops() {
        return upStops;
    }

    public void setUpStops(PriorityQueue<Integer> upStops) {
        this.upStops = upStops;
    }

    public PriorityQueue<Integer> getDownStops() {
        return downStops;
    }

    public void setDownStops(PriorityQueue<Integer> downStops) {
        this.downStops = downStops;
    }
}
