package events;


import exceptions.BookingNotFoundException;
import services.BookingOrchestrator;

public class SimpleEventPublisher implements EventPublisher {

    private BookingOrchestrator orchestrator;

    public SimpleEventPublisher(BookingOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @Override
    public void publish(Object event) {
        if (event instanceof BookingCreatedEvent) {
            // simulate async
            Thread t = new Thread(() -> {
                try {
                    this.orchestrator.handle((BookingCreatedEvent) event);
                } catch (BookingNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            t.start();
        }
    }
}