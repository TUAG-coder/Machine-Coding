package models;

import java.util.List;

// @Entity
public class CarButtonPanel extends BaseModel{
    private ElevatorCar elevatorCar;
    private List<CarButton> carButtons;

    public ElevatorCar getElevatorCar() {
        return elevatorCar;
    }

    public void setElevatorCar(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
    }

    public List<CarButton> getCarButtons() {
        return carButtons;
    }

    public void setCarButtons(List<CarButton> carButtons) {
        this.carButtons = carButtons;
    }
}
