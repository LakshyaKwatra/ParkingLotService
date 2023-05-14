package parking.lot.utils;

import parking.lot.entities.Floor;
import parking.lot.entities.Slot;
import parking.lot.enums.SlotType;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceUtil {
    public static List<Floor> getFloorListWithDefaultSlots(String parkingLotId, int numberOfFloors, int numberOfSlotsPerFloor) {
        List<Floor> floors = new ArrayList<>(numberOfFloors);
        for(int i = 1; i <= numberOfFloors; i++) {
            List<Slot> slots = getDefaultSlotList(numberOfSlotsPerFloor, i);
            Floor floor = new Floor(i, parkingLotId, slots);
            floors.add(floor);
        }
        return floors;
    }

    public static SlotType getSlotTypeFromString(String slotType) {
        SlotType type;
        try {
            type = SlotType.valueOf(slotType.toUpperCase());
        } catch (Exception e) {
            return null;
        }
        return type;
    }

    public static void printSlotList(List<Slot> slots) {
        for(int i = 0; i < slots.size(); i++) {
            System.out.print(slots.get(i).getSlotNumber());
            if(i != slots.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.println("");
    }

    private static List<Slot> getDefaultSlotList(int numberOfSlots, int floorNumber) {
        List<Slot> slots = new ArrayList<>(numberOfSlots);
        slots.add(createSlotObject(1, SlotType.TRUCK, floorNumber));
        slots.add(createSlotObject(2, SlotType.BIKE, floorNumber));
        slots.add(createSlotObject(3, SlotType.BIKE, floorNumber));
        for(int i = 4; i <= numberOfSlots; i++) {
            slots.add(new Slot(i, SlotType.CAR, floorNumber));
        }
        return slots;
    }

    private static Slot createSlotObject(int slotNumber, SlotType slotType, int floorNumber) {
        return new Slot(slotNumber, slotType, floorNumber);
    }

}
