package models;

import enums.CarButtonType;

// @Entity
public class CarButton extends BaseModel {
    private String label;
    private CarButtonType buttonType;
    private ElevatorCar elevatorCar;

    // Transient
    private Boolean isPressed;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public CarButtonType getButtonType() {
        return buttonType;
    }

    public void setButtonType(CarButtonType buttonType) {
        this.buttonType = buttonType;
    }

    public ElevatorCar getElevatorCar() {
        return elevatorCar;
    }

    public void setElevatorCar(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
    }

    public Boolean getPressed() {
        return isPressed;
    }

    public void setPressed(Boolean pressed) {
        isPressed = pressed;
    }
}
