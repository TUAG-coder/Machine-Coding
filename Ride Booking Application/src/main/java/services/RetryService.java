package services;

import models.Booking;

public class RetryService {
    public boolean shouldRetry(Booking booking) {
        if (booking.getDriver() != null) {
            return false;
        }
        if (booking.isTerminalState()) {
            return false;
        }

        if (booking.canRetry()) {
            booking.incrementRetryCount();
            return true;
        } else {
            booking.expire();
            return false;
        }
    }
}
