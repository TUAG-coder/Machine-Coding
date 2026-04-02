package events;

public class BookingCreatedEvent {
    private Long bookingId;

    public BookingCreatedEvent(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getBookingId() {
        return bookingId;
    }
}
