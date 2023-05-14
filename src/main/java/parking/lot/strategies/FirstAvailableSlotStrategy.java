package parking.lot.strategies;

import parking.lot.entities.ParkingLot;
import parking.lot.entities.Slot;
import parking.lot.enums.SlotType;

public interface FirstAvailableSlotStrategy {
    Slot getFirstAvailableSlot(ParkingLot parkingLot, SlotType vehicleType);
}
