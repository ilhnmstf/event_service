package post_service.service.post;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import post_service.dto.CreateEventDto;
import post_service.dto.EventDto;
import post_service.entity.Event;
import post_service.mapper.EventMapper;
import post_service.repository.cache.EventCacheRepository;
import post_service.repository.db.EventRepository;
import post_service.service.participant.ParticipantService;
import post_service.service.user.UserService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EventServiceImpl implements EventService {
    private final UserService userService;
    private final ParticipantService participantService;
    private final EventRepository eventRepository;
    private final EventCacheRepository eventCacheRepository;
    private final EventMapper eventMapper;
    
    @Override
    public EventDto createEvent(CreateEventDto createEvent) {
        userService.validateUserById(createEvent.getOwnerId());
        EventDto event = saveAndConvert(eventMapper.toEntity(createEvent));
        log.debug("create event {} ", event);
        return event;
    }

    @Override
    public EventDto getEventById(long eventId) {
        EventDto event = eventCacheRepository.get(eventId)
                .orElseGet(() -> eventCacheRepository.saveOptimistic(eventMapper.toDto(findNotDeleted(eventId))));
        log.info("Get event {}", event);
        return event;
    }

    @Override
    public EventDto updateEvent(long eventId, CreateEventDto updatedEvent) {
        userService.validateUserById(updatedEvent.getOwnerId());
        EventDto event = saveAndConvert(eventMapper.update(findNotDeleted(eventId), updatedEvent));
        log.debug("Updated event {}", event);
        return event;
    }

    @Override
    public void deleteEventById(long eventId) {
        eventRepository.save(find(eventId)
                .setDeleted(true)
                .setDeletedAt(LocalDateTime.now())
                .setActive(false));
        eventCacheRepository.delete(eventId);
        log.debug("Delete event with id {}", eventId);
    }

    @Override
    public void subscribe(long eventId, long userId) {
        userService.validateUserById(userId);

        participantService.save(findNotDeleted(eventId), userId);
        saveAndConvert(findNotDeleted(eventId));
        log.debug("User with id {} subscribe on event with id {}", userId, eventId);
    }

    @Override
    public void unSubscribe(long eventId, long userId) {
        userService.validateUserById(userId);

        participantService.delete(find(eventId), userId);
        saveAndConvert(find(eventId));
        log.debug("User with id {} unsubscribe on event with id {}", userId, eventId);
    }

    private EventDto saveAndConvert(Event event) {
        return eventCacheRepository.saveOptimistic(eventMapper.toDto(eventRepository.save(event)));
    }

    private Event find(long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("event with id " + eventId + " not exists"));
        log.debug("found event {}", event);
        return event;
    }

    private Event findNotDeleted(long eventId) {
        Event event = find(eventId);
        if (event.isDeleted()) {
            throw new IllegalArgumentException("Event with id " + event + " was deleted");
        }

        log.debug("Event with id {} is not deleted", event);
        return event;
    }
}