package exceptions;

import models.MeetingRoom;

public class MeetingRoomNotFoundException extends RuntimeException {
    public MeetingRoomNotFoundException() {
        super();
    }

    public MeetingRoomNotFoundException(String message) {
        super(message);
    }
}
