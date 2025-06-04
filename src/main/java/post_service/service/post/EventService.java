package post_service.service.post;

import post_service.dto.event.CreateEventDto;
import post_service.dto.event.EventDto;
import post_service.entity.Event;

public interface EventService {

    EventDto create(CreateEventDto createPost);

    Event findById(long postId);
}
