import controllers.SchedulerController;
import models.dtos.ScheduleMeetingRequestDto;
import models.dtos.ScheduleMeetingResponseDto;
import observer.EmailNotificationService;
import observer.SmsNotificationService;
import repositories.MeetingRepository;
import repositories.MeetingRoomRepository;
import repositories.UserRepository;
import repositories.impl.MeetingRepositoryImpl;
import repositories.impl.MeetingRoomRepositoryImpl;
import repositories.impl.UserRepositoryImpl;
import services.impl.SchedulerServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();
        MeetingRepository meetingRepository = new MeetingRepositoryImpl();
        MeetingRoomRepository meetingRoomRepository = new MeetingRoomRepositoryImpl();

        SchedulerServiceImpl schedulerServiceImpl = new SchedulerServiceImpl(
                userRepository,
                meetingRepository,
                meetingRoomRepository);

        schedulerServiceImpl.addObserver(new EmailNotificationService());
        schedulerServiceImpl.addObserver(new SmsNotificationService());

        SchedulerController schedulerController = new SchedulerController(schedulerServiceImpl);
        ScheduleMeetingRequestDto requestDto = new ScheduleMeetingRequestDto();
        ScheduleMeetingResponseDto responseDto = schedulerController.scheduleMeeting(requestDto);

        System.out.println(responseDto.toString());
    }
}
