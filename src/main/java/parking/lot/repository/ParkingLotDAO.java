package parking.lot.repository;

import parking.lot.entities.ParkingLot;

import java.util.HashMap;

public class ParkingLotDAO {
    private HashMap<String, ParkingLot> parkingLotData;
    private static ParkingLotDAO instance;

    private ParkingLotDAO() {
        this.parkingLotData = new HashMap<>();
    }

    public ParkingLot getParkingLotById(String parkingLotId) {
        return parkingLotData.get(parkingLotId);
    }

    public static ParkingLotDAO getInstance() {
        if(instance == null) {
            instance = new ParkingLotDAO();
        }
        return instance;
    }

    public void addParkingLot(ParkingLot parkingLot) {
        if(parkingLotData.containsKey(parkingLot.getParkingLotId())) {
           System.out.println("Parking lot already exists.");
        } else {
            parkingLotData.put(parkingLot.getParkingLotId(), parkingLot);
        }
    }


}
