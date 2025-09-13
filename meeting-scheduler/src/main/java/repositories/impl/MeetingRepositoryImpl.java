package repositories.impl;

import models.Meeting;
import repositories.MeetingRepository;

import java.util.HashMap;
import java.util.Map;

public class MeetingRepositoryImpl implements MeetingRepository {
    private Map<Long, Meeting> meetingMap = new HashMap<>();
    @Override
    public Meeting createNewMeeting(Meeting meeting) {
        long id = (long) meetingMap.size() + 1;
        meetingMap.put(id, meeting);
        meeting.setId(id);
        return meeting;
    }
}
