package parking.lot.services;

import parking.lot.entities.Floor;
import parking.lot.entities.ParkingLot;
import parking.lot.entities.Slot;
import parking.lot.enums.SlotType;
import parking.lot.strategies.FirstAvailableSlotStrategy;

import java.util.*;

public class SlotAvailabilityService {
    private FirstAvailableSlotStrategy firstAvailableSlotStrategy;

    public SlotAvailabilityService(FirstAvailableSlotStrategy firstAvailableSlotStrategy) {
        this.firstAvailableSlotStrategy = firstAvailableSlotStrategy;
    }

    public HashMap<Integer,List<Slot>> getFreeSlots(ParkingLot parkingLot, SlotType vehicleType) {
        if(Objects.isNull(parkingLot) || Objects.isNull(vehicleType)) {
            return null;
        }
        HashMap<Integer,List<Slot>> freeSlots = new HashMap<>();
        for(Floor floor: parkingLot.getFloors()) {
            for(Slot slot: floor.getSlots()) {
                if(isSlotAvailable(slot, vehicleType)) {
                    freeSlots.computeIfAbsent(floor.getFloorNumber(), k -> new ArrayList<>()).add(slot);
                }
            }
        }
        return freeSlots;
    }

    public HashMap<Integer,List<Slot>> getOccupiedSlots(ParkingLot parkingLot, SlotType vehicleType) {
        if(Objects.isNull(parkingLot) || Objects.isNull(vehicleType)) {
            return null;
        }
        HashMap<Integer,List<Slot>> freeSlots = new HashMap<>();
        for(Floor floor: parkingLot.getFloors()) {
            for(Slot slot: floor.getSlots()) {
                if(slot.isOccupied() && slot.getSlotType().equals(vehicleType)) {
                    freeSlots.computeIfAbsent(floor.getFloorNumber(), k -> new ArrayList<>()).add(slot);
                }
            }
        }
        return freeSlots;
    }

    public HashMap<Integer, Integer> getFreeCountMap(ParkingLot parkingLot, SlotType vehicleType) {
        if(Objects.isNull(parkingLot) || Objects.isNull(vehicleType)) {
            return null;
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        for(Floor floor: parkingLot.getFloors()) {
            int count = 0;
            for(Slot slot: floor.getSlots()) {
                if(isSlotAvailable(slot, vehicleType)) {
                    count++;
                }
            }
            map.put(floor.getFloorNumber(), count);
        }
        return map;
    }

    public Slot getFirstAvailableSlot(ParkingLot parkingLot, SlotType vehicleType) {
        if(Objects.isNull(parkingLot) || Objects.isNull(vehicleType)) {
            return null;
        }
        return firstAvailableSlotStrategy.getFirstAvailableSlot(parkingLot, vehicleType);
    }

    public boolean isSlotAvailable(Slot slot, SlotType vehicleType) {
        return vehicleType.equals(slot.getSlotType()) && !slot.isOccupied();
    }

    public boolean isFloorAvailable(Floor floor, SlotType vehicleType) {
        for(Slot slot: floor.getSlots()) {
            if(isSlotAvailable(slot, vehicleType)) {
                return true;
            }
        }
        return false;
    }

    public Slot getSlotBySlotNumberAndFloorNumber(ParkingLot parkingLot, int slotNumber, int floorNumber) {
        List<Floor> floors = parkingLot.getFloors();
        for(Floor floor: floors) {
            if(floor.getFloorNumber() == floorNumber) {
                for(Slot slot: floor.getSlots()) {
                    if(slot.getSlotNumber() == slotNumber) {
                        return slot;
                    }
                }
            }
        }
        return null;
    }

}
