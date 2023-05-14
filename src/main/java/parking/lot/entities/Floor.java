package parking.lot.entities;

import java.util.List;

public class Floor {
    private final int floorNumber;
    private final String parkingLotId;
    private List<Slot> slots;


    public Floor(int floorNumber, String parkingLotId, List<Slot> slots) {
        this.floorNumber = floorNumber;
        this.parkingLotId = parkingLotId;
        this.slots = slots;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }
}
