package parking.lot.entities;

import parking.lot.enums.SlotType;

public class Slot {
    private final int slotNumber;
    private final int floorNumber;
    private SlotType slotType;
    private boolean isOccupied;
    private String vehicleRegistrationNumber;

    public Slot(int slotNumber, SlotType slotType, int floorNumber) {
        this.slotNumber = slotNumber;
        this.slotType = slotType;
        this.floorNumber = floorNumber;
        this.isOccupied = false;
        this.vehicleRegistrationNumber = null;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public void setSlotType(SlotType slotType) {
        this.slotType = slotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }
}
