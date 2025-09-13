package services;

import models.Meeting;

import java.util.Date;
import java.util.List;

public interface SchedulerService {
    Meeting scheduleMeeting(
            String title, Date startTime, Date endTime, String roomName,
            String organizer, List<String> participants);
}
