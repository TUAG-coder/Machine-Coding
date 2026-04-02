package services;

import models.Booking;
import models.Driver;

public class DriverNotificationService {

    public void notify(Driver driver, Booking booking) {
        // simulate sending notification
        System.out.println("Notifying driver " + driver.getId() + " about booking " + booking.getId());

        // After that POST request can be sent to acceptRide() method in DriverController class using UI/Postman
    }
}
