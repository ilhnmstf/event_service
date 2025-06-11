package post_service.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import post_service.dto.CreateEventDto;
import post_service.dto.EventDto;
// TODO create swagger doc

public interface EventController {

    ResponseEntity<EventDto> create(@RequestBody @Valid CreateEventDto createPost);

    ResponseEntity<EventDto> get(@PathVariable long eventId);

    ResponseEntity<EventDto> update(@PathVariable long eventId, @RequestBody @Valid  CreateEventDto updatedEvent);

    ResponseEntity<Void> delete(@PathVariable long eventId);

    ResponseEntity<Void> subscribe(@PathVariable long eventId, @RequestParam long userId);

    ResponseEntity<Void> unSubscribe(@PathVariable long eventId, @RequestParam long userId);
}