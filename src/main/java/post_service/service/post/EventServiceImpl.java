package post_service.service.post;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import post_service.dto.event.CreateEventDto;
import post_service.dto.event.EventDto;
import post_service.entity.Event;
import post_service.mapper.EventMapper;
import post_service.repository.db.EventRepository;
import post_service.service.user.UserService;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final UserService userService;
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public EventDto create(CreateEventDto createEvent) {
        return null;
    }

    @Override
    public Event findById(long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event with id " + eventId + " not exists"));
    }
}
