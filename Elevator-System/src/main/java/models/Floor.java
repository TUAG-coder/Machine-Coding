package models;

// @Entity
public class Floor extends BaseModel{
    private int floorNumber;
    private HallButtonPanel hallButtonPanel;

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public HallButtonPanel getHallButtonPanel() {
        return hallButtonPanel;
    }

    public void setHallButtonPanel(HallButtonPanel hallButtonPanel) {
        this.hallButtonPanel = hallButtonPanel;
    }
}
