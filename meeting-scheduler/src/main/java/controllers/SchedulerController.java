package controllers;

import enums.ResponseStatus;
import models.Meeting;
import models.dtos.ScheduleMeetingRequestDto;
import models.dtos.ScheduleMeetingResponseDto;
import services.SchedulerService;

public class SchedulerController {
    private SchedulerService schedulerService;

    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    public ScheduleMeetingResponseDto scheduleMeeting(ScheduleMeetingRequestDto requestDto) {
        ScheduleMeetingResponseDto responseDto = new ScheduleMeetingResponseDto();
        try {
            Meeting meeting = this.schedulerService.scheduleMeeting(
                    requestDto.getTitle(), requestDto.getStartTime(), requestDto.getEndTime(),
                    requestDto.getRoomName(), requestDto.getOrganizer(), requestDto.getParticipants());
            responseDto.setMeetingId(meeting.getId());
            responseDto.setMeetingTitle(meeting.getTitle());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception exception) {
            responseDto.setResponseStatus(ResponseStatus.ERROR);
        }
        return responseDto;
    }
}
