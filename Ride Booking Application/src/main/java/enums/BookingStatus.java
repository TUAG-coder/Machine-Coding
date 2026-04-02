package enums;

import java.util.Arrays;
import java.util.Optional;

public enum BookingStatus {
    INITIATED,
    SEARCHING_DRIVER,
    DRIVER_ASSIGNED,
    DRIVER_ARRIVING,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED,
    EXPIRED
}
