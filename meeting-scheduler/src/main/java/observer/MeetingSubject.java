package observer;

import models.Meeting;

public interface MeetingSubject {
    void addObserver(Observer observer);
    void deleteObserver(Observer observer);
    void notifyObserver(Meeting meeting);
}
