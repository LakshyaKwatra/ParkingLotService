package parking.lot;

import parking.lot.services.ParkingLotService;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        ParkingLotService parkingLotService = new ParkingLotService();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String command = scanner.nextLine();
            String[] commandSplit = command.split(" ");
            String commandType = commandSplit[0];
            String defaultParkingLotId = "PR1234";

            switch (commandType) {
                case "create_parking_lot":
                    parkingLotService.createParkingLot(commandSplit[1], Integer.parseInt(commandSplit[2]),Integer.parseInt(commandSplit[3]));
                case "display":
                    String slotType = commandSplit[2];
                    switch (commandSplit[1]) {
                        case "free_count":
                            parkingLotService.displayFreeCount(defaultParkingLotId, slotType);
                            break;
                        case "free_slots":
                            parkingLotService.displayFreeSlots(defaultParkingLotId, slotType);
                            break;
                        case "occupied_slots":
                            parkingLotService.displayOccupiedSlots(defaultParkingLotId, slotType);
                            break;
                    }
                    break;
                case "park_vehicle":
                    parkingLotService.parkVehicle(defaultParkingLotId, commandSplit[2], commandSplit[1], commandSplit[3]);
                    break;
                case "unpark_vehicle":
                    parkingLotService.unparkVehicle(commandSplit[1]);
                    break;
            }
        }
    }
}