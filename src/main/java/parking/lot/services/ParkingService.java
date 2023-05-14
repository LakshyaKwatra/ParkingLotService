package parking.lot.services;

import parking.lot.entities.ParkingLot;
import parking.lot.entities.Slot;
import parking.lot.entities.Vehicle;
import parking.lot.repository.ParkingLotDAO;
import parking.lot.repository.VehicleDAO;
import parking.lot.strategies.LowestFloorAndSlotNumberStrategy;

import java.util.Objects;

public class ParkingService {

    private final SlotAvailabilityService slotAvailabilityService = new SlotAvailabilityService(new LowestFloorAndSlotNumberStrategy());
    private final ParkingLotDAO parkingLotDAO = ParkingLotDAO.getInstance();
    private final VehicleDAO vehicleDAO = VehicleDAO.getInstance();


    public String parkAndGetTicket(ParkingLot parkingLot, Vehicle vehicle) {
        Slot slot = slotAvailabilityService.getFirstAvailableSlot(parkingLot, vehicle.getVehicleSlotType());
        if(Objects.isNull(slot)) {
            return null;
        }
        vehicle.setIsParked(true);
        vehicleDAO.addVehicle(vehicle);
        slot.setIsOccupied(true);
        slot.setVehicleRegistrationNumber(vehicle.getRegistrationNumber());
        return generateTicket(parkingLot.getParkingLotId(), slot);
    }

    public Vehicle unpark(String ticket) {
        Slot slot = validateTicketAndGetSlot(ticket);
        if(Objects.isNull(slot)) {
            return null;
        }
        Vehicle vehicle = vehicleDAO.getVehicleByRegistrationNumber(slot.getVehicleRegistrationNumber());
        if(Objects.isNull(vehicle)) {
            return null;
        }
        vehicle.setIsParked(false);
//        vehicleDAO.updateVehicle(vehicle);
        slot.setIsOccupied(false);
        slot.setVehicleRegistrationNumber(null);
        return vehicle;
    }

    private String generateTicket(String parkingLotId, Slot slot) {
        return parkingLotId+"_"+slot.getFloorNumber()+"_"+slot.getSlotNumber();
    }

    private Slot validateTicketAndGetSlot(String ticket) {
        String[] data = ticket.split("_");
        if(data.length != 3) {
            return null;
        }
        String ticketParkingLotId = data[0];
        int ticketFloorNumber = Integer.parseInt(data[1]);
        int ticketSlotNumber = Integer.parseInt(data[2]);

        ParkingLot ticketParkingLot = parkingLotDAO.getParkingLotById(ticketParkingLotId);
        if(Objects.isNull(ticketParkingLot)) {
            return null;
        }

        Slot slot = slotAvailabilityService.getSlotBySlotNumberAndFloorNumber(ticketParkingLot, ticketSlotNumber, ticketFloorNumber);
        if(Objects.isNull(slot) || !slot.isOccupied()) {
            return null;
        }
        return slot;
    }
}
