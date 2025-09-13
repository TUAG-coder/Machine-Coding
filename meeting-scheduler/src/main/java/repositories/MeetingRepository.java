package repositories;

import models.Meeting;

public interface MeetingRepository {
    Meeting createNewMeeting(Meeting meeting);
}
