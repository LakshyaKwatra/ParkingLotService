package parking.lot.repository;

import parking.lot.entities.Vehicle;

import java.util.HashMap;

public class VehicleDAO {
    private HashMap<String, Vehicle> vehicleData;
    private static VehicleDAO instance;

    private VehicleDAO() {
        this.vehicleData = new HashMap<>();
    }

    public Vehicle getVehicleByRegistrationNumber(String registrationNumber) {
        return vehicleData.get(registrationNumber);
    }

    public static VehicleDAO getInstance() {
        if(instance == null) {
            instance = new VehicleDAO();
        }
        return instance;
    }

    public void addVehicle(Vehicle vehicle) {
        if(!vehicleData.containsKey(vehicle.getRegistrationNumber())) {
            vehicleData.put(vehicle.getRegistrationNumber(), vehicle);
        }
    }

    public void updateVehicle(Vehicle vehicle) {
        vehicleData.put(vehicle.getRegistrationNumber(), vehicle);
    }
}
