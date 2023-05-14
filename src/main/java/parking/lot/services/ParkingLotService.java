package parking.lot.services;

import parking.lot.entities.*;
import parking.lot.enums.SlotType;
import parking.lot.repository.ParkingLotDAO;
import parking.lot.strategies.LowestFloorAndSlotNumberStrategy;
import parking.lot.utils.ParkingLotServiceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ParkingLotService {
    private final ParkingLotDAO parkingLotDAO = ParkingLotDAO.getInstance();
    private final ParkingService parkingService = new ParkingService();
    private final SlotAvailabilityService slotAvailabilityService = new SlotAvailabilityService(new LowestFloorAndSlotNumberStrategy());

    public void createParkingLot(String parkingLotId, int numberOfFloors, int numberOfSlotsPerFloor) {
        if(Objects.isNull(parkingLotId) || numberOfFloors < 0 || numberOfSlotsPerFloor < 0) {
            return;
        }
        ParkingLot parkingLot = new ParkingLot(parkingLotId, numberOfFloors, numberOfSlotsPerFloor);
        parkingLotDAO.addParkingLot(parkingLot);
        System.out.println(String.format("Created Parking Lot with %d floors and %d slots per floor", numberOfFloors, numberOfSlotsPerFloor));
    }

    public void parkVehicle(String parkingLotId, String registrationNumber, String vehicleType, String color) {
        if(Objects.isNull(parkingLotId) || Objects.isNull(registrationNumber) || Objects.isNull(vehicleType) || Objects.isNull(color)) {
            System.out.println("Invalid input.");
            return;
        }
        if(parkingLotId.isBlank() || registrationNumber.isBlank() || color.isBlank()) {
            System.out.println("Invalid input.");
            return;
        }
        ParkingLot parkingLot = parkingLotDAO.getParkingLotById(parkingLotId);
        if(Objects.isNull(parkingLot)) {
            System.out.println("Invalid parking lot.");
            return;
        }
        SlotType type = ParkingLotServiceUtil.getSlotTypeFromString(vehicleType);
        if(Objects.isNull(type)) {
            System.out.println("Invalid vehicle type.");
            return;
        }
        Vehicle vehicle = new Vehicle(registrationNumber, type, color);
        String ticket = parkingService.parkAndGetTicket(parkingLot, vehicle);
        if(Objects.isNull(ticket)) {
            System.out.println("Parking lot full.");
            return;
        }
        System.out.println(String.format("Parked Vehicle. Ticket ID: %s", ticket));
    }

    public void unparkVehicle(String ticket) {
        Vehicle vehicle = parkingService.unpark(ticket);
        if(Objects.isNull(vehicle)) {
            System.out.println("Invalid ticket.");
            return;
        }
        System.out.println(String.format("Unparked vehicle with Registration Number: %s and Color: %s", vehicle.getRegistrationNumber(), vehicle.getColor()));
    }

    public void displayFreeCount(String parkingLotId, String vehicleType) {
        SlotType type = ParkingLotServiceUtil.getSlotTypeFromString(vehicleType);
        if(Objects.isNull(type)) {
            return;
        }
        ParkingLot parkingLot = parkingLotDAO.getParkingLotById(parkingLotId);
        HashMap<Integer,Integer> freeCountMap = slotAvailabilityService.getFreeCountMap(parkingLot, ParkingLotServiceUtil.getSlotTypeFromString(vehicleType));
        for(int floorNumber: freeCountMap.keySet()) {
            System.out.println(String.format("No. of free slots for %s on Floor %d: %d", vehicleType, floorNumber, freeCountMap.get(floorNumber)));
        }
    }

    public void displayFreeSlots(String parkingLotId, String vehicleType) {
        SlotType type = ParkingLotServiceUtil.getSlotTypeFromString(vehicleType);
        if(Objects.isNull(type)) {
            return;
        }
        ParkingLot parkingLot = parkingLotDAO.getParkingLotById(parkingLotId);
        HashMap<Integer, List<Slot>> freeSlotsMap = slotAvailabilityService.getFreeSlots(parkingLot, ParkingLotServiceUtil.getSlotTypeFromString(vehicleType));
        for(int floorNumber: freeSlotsMap.keySet()) {
            System.out.print(String.format("No. of free slots for %s on Floor %d: ", vehicleType, floorNumber));
            ParkingLotServiceUtil.printSlotList(freeSlotsMap.get(floorNumber));
        }
    }

    public void displayOccupiedSlots(String parkingLotId, String vehicleType) {
        SlotType type = ParkingLotServiceUtil.getSlotTypeFromString(vehicleType);
        if(Objects.isNull(type)) {
            return;
        }
        ParkingLot parkingLot = parkingLotDAO.getParkingLotById(parkingLotId);
        HashMap<Integer, List<Slot>> occupiedSlotsMap = slotAvailabilityService.getOccupiedSlots(parkingLot, ParkingLotServiceUtil.getSlotTypeFromString(vehicleType));
        for(int floorNumber: occupiedSlotsMap.keySet()) {
            System.out.print(String.format("No. of occupied slots for %s on Floor %d: ", vehicleType, floorNumber));
            ParkingLotServiceUtil.printSlotList(occupiedSlotsMap.get(floorNumber));
        }
    }
}
