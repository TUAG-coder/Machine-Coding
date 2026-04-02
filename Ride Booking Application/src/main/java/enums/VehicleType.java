package enums;

import java.util.Arrays;
import java.util.Optional;

public enum VehicleType {
    TWO_WHEELER,
    CAB,
    CAB_PREMIUM,
    CAB_OUTSTATION;
    public static Optional<VehicleType> getVehicleType(String vehicleType) {
        return Arrays.stream(VehicleType.values())
                .filter(type -> type.name().equalsIgnoreCase(vehicleType))
                .findFirst();
    }
}
