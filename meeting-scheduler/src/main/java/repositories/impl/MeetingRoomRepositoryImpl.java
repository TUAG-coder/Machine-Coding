package repositories.impl;

import models.MeetingRoom;
import repositories.MeetingRoomRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MeetingRoomRepositoryImpl implements MeetingRoomRepository {
    private Map<String, MeetingRoom> roomMap = new HashMap<>();
    @Override
    public Optional<MeetingRoom> findMeetingRoomByName(String roomName) {
        if (roomMap.containsKey(roomName)) {
            return Optional.of(roomMap.get(roomName));
        }
        return Optional.empty();
    }
}
