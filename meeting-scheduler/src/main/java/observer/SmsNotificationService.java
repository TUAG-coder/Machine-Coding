package observer;

import models.Meeting;
import models.User;

public class SmsNotificationService implements Observer{
    @Override
    public void consume(Meeting meeting) {
        for (User user: meeting.getParticipants()) {
            System.out.println(
                    "SMS notification sent to user " + user.getName() +
                            "notiffication about meeting " + meeting.getTitle());
        }
    }
}
