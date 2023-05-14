package parking.lot.strategies;

import parking.lot.entities.Floor;
import parking.lot.entities.ParkingLot;
import parking.lot.entities.Slot;
import parking.lot.enums.SlotType;
import parking.lot.services.SlotAvailabilityService;

import java.util.Objects;

public class LowestFloorAndSlotNumberStrategy implements FirstAvailableSlotStrategy{

    private final SlotAvailabilityService slotAvailabilityService = new SlotAvailabilityService(this);
    @Override
    public Slot getFirstAvailableSlot(ParkingLot parkingLot, SlotType vehicleType) {
        Floor floor = getLowestAvailableFloor(parkingLot, vehicleType);
        if(Objects.isNull(floor)) {
            return null;
        }
        return getLowestAvailableSlot(floor, vehicleType);
    }

    private Slot getLowestAvailableSlot(Floor floor, SlotType vehicleType) {
        int minimumSlotNumber = Integer.MAX_VALUE;
        Slot lowestAvailableSlot = null;
        for(Slot slot: floor.getSlots()) {
            if(slotAvailabilityService.isSlotAvailable(slot, vehicleType)) {
                if(slot.getSlotNumber() < minimumSlotNumber) {
                    minimumSlotNumber = slot.getSlotNumber();
                    lowestAvailableSlot = slot;
                }
            }
        }
        return lowestAvailableSlot;
    }

    private Floor getLowestAvailableFloor(ParkingLot parkingLot, SlotType vehicleType) {
        int minimumFloorNumber = Integer.MAX_VALUE;
        Floor lowestAvailableFloor = null;
        for(Floor floor: parkingLot.getFloors()) {
            if(slotAvailabilityService.isFloorAvailable(floor, vehicleType)) {
                if(floor.getFloorNumber() < minimumFloorNumber) {
                    minimumFloorNumber = floor.getFloorNumber();
                    lowestAvailableFloor = floor;
                }
            }
        }
        return lowestAvailableFloor;
    }

}
