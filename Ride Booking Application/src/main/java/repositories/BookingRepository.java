package repositories;

import models.Booking;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BookingRepository {
    private Map<Long, Booking> bookingMap = new HashMap<>();

    public void createBooking(Booking booking) {
        Long id = (long) bookingMap.size();
        booking.setId(id);
        booking.setCreatedAt(new Date());
        bookingMap.put(id, booking);
    }

    public Optional<Booking> findById(Long id) {
        if (bookingMap.containsKey(id)) {
            return Optional.of(bookingMap.get(id));
        }
        return Optional.empty();
    }
}
