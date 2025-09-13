package models;

import java.util.List;

// @Entity
public class HallButtonPanel extends BaseModel{
    private Floor floor;
    private List<HallButton> hallButtonList;

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public List<HallButton> getHallButtonList() {
        return hallButtonList;
    }

    public void setHallButtonList(List<HallButton> hallButtonList) {
        this.hallButtonList = hallButtonList;
    }
}
