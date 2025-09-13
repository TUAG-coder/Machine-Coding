package observer;

import models.Meeting;

public interface Observer {
    void consume(Meeting meeting);
}
