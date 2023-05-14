package parking.lot.entities;

import parking.lot.enums.SlotType;

public class Vehicle {
    String registrationNumber;
    SlotType vehicleSlotType;
    String color;
    boolean isParked;

    public Vehicle(String registrationNumber, SlotType vehicleSlotType, String color) {
        this.registrationNumber = registrationNumber;
        this.vehicleSlotType = vehicleSlotType;
        this.color = color;
        this.isParked = false;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public SlotType getVehicleSlotType() {
        return vehicleSlotType;
    }

    public void setVehicleSlotType(SlotType vehicleSlotType) {
        this.vehicleSlotType = vehicleSlotType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean getIsParked() {
        return isParked;
    }

    public void setIsParked(boolean parked) {
        isParked = parked;
    }
}
