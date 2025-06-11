package post_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import post_service.dto.CreateEventDto;
import post_service.dto.EventDto;
import post_service.service.post.EventService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/events")
public class EventControllerImpl implements EventController {
    private final EventService eventService;

    @PostMapping
    @Override
    public ResponseEntity<EventDto> create(CreateEventDto createPost) {
        return ResponseEntity.ok().body(eventService.createEvent(createPost));
    }

    @GetMapping("/{eventId}")
    @Override
    public ResponseEntity<EventDto> get(long eventId) {
        return ResponseEntity.ok().body(eventService.getEventById(eventId));
    }

    @PutMapping("/{eventId}")
    @Override
    public ResponseEntity<EventDto> update(long eventId, CreateEventDto updatedEvent) {
        return ResponseEntity.ok().body(eventService.updateEvent(eventId, updatedEvent));
    }

    @DeleteMapping("/{eventId}")
    @Override
    public ResponseEntity<Void> delete(long eventId) {
        eventService.deleteEventById(eventId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{eventId}/subscribe")
    @Override
    public ResponseEntity<Void> subscribe(long eventId, long userId) {
        eventService.subscribe(eventId, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{eventId}/unsubscribe")
    @Override
    public ResponseEntity<Void> unSubscribe(long eventId, long userId) {
        eventService.unSubscribe(eventId, userId);
        return ResponseEntity.ok().build();
    }
}