package models;

// @Entity
public class Display extends BaseModel{
    private Floor floor;
    private Boolean isInsideElevator;

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Boolean getInsideElevator() {
        return isInsideElevator;
    }

    public void setInsideElevator(Boolean insideElevator) {
        isInsideElevator = insideElevator;
    }
}
