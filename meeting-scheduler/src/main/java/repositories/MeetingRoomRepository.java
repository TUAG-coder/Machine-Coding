package repositories;

import models.MeetingRoom;

import java.util.Optional;

public interface MeetingRoomRepository {
    Optional<MeetingRoom> findMeetingRoomByName(String roomName);
}
