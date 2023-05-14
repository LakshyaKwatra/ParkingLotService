package parking.lot.entities;

import parking.lot.utils.ParkingLotServiceUtil;

import java.util.List;

public class ParkingLot {
    private String parkingLotId;
    private List<Floor> floors;

    public ParkingLot(String parkingLotId, List<Floor> floors) {
        this.parkingLotId = parkingLotId;
        this.floors = floors;
    }
    public ParkingLot(String parkingLotId, int numberOfFloors, int numberOfSlotsPerFloor) {
        this.parkingLotId = parkingLotId;
        this.floors = ParkingLotServiceUtil.getFloorListWithDefaultSlots(parkingLotId, numberOfFloors, numberOfSlotsPerFloor);
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

}
