package services.impl;

import enums.MeetingStatus;
import exceptions.MeetingRoomNotFoundException;
import exceptions.UserNotFoundException;
import models.Meeting;
import models.MeetingRoom;
import models.User;
import observer.MeetingSubject;
import observer.Observer;
import repositories.MeetingRepository;
import repositories.MeetingRoomRepository;
import repositories.UserRepository;
import services.SchedulerService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SchedulerServiceImpl implements SchedulerService, MeetingSubject {
    private UserRepository userRepository;
    private MeetingRepository meetingRepository;
    private MeetingRoomRepository meetingRoomRepository;

    private List<Observer> observers = new ArrayList<>();

    public SchedulerServiceImpl(UserRepository userRepository,
                                MeetingRepository meetingRepository,
                                MeetingRoomRepository meetingRoomRepository) {
        this.userRepository = userRepository;
        this.meetingRepository = meetingRepository;
        this.meetingRoomRepository = meetingRoomRepository;
    }

    @Override
    public Meeting scheduleMeeting(
            String title, Date startTime, Date endTime, String roomName,
            String organizerName, List<String> participantNames) {
        Optional<MeetingRoom> optionalMeetingRoom = this.meetingRoomRepository.findMeetingRoomByName(roomName);
        if (optionalMeetingRoom.isEmpty()) {
            throw new MeetingRoomNotFoundException("");
        }
        MeetingRoom meetingRoom = optionalMeetingRoom.get();

        Optional<User> optionalUser = this.userRepository.findUserByName(organizerName);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("Given organizerName doesn't exist.");
        }
        User organizer = optionalUser.get();

        List<User> participants = this.userRepository.findUsersByName(participantNames);
        if (participants.size() != participantNames.size()) {
            throw new UserNotFoundException("");
        }

        Meeting meeting = new Meeting();
        meeting.setTitle(title);
        meeting.setStartTime(startTime);
        meeting.setEndTime(endTime);
        meeting.setMeetingRoom(meetingRoom);
        meeting.setOrganizer(organizer);
        meeting.setParticipants(participants);
        meeting.setStatus(MeetingStatus.SCHEDULED);
        meeting = this.meetingRepository.createNewMeeting(meeting);

        notifyObserver(meeting);

        return meeting;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(Meeting meeting) {
        for (Observer observer: observers) {
            observer.consume(meeting);
        }
    }
}
