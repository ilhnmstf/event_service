package post_service.service.post;

import post_service.dto.CreateEventDto;
import post_service.dto.EventDto;

public interface EventService {

    EventDto createEvent(CreateEventDto createPost);

    EventDto getEventById(long eventId);

    EventDto updateEvent(long eventId, CreateEventDto updatedEvent);

    void deleteEventById(long eventId);

    void subscribe(long eventId, long userId);

    void unSubscribe(long eventId, long userId);
}