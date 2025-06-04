package post_service.controller.event;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import post_service.dto.event.CreateEventDto;
import post_service.dto.event.EventDto;
// TODO create doc

public interface EventController {
    ResponseEntity<EventDto> create(@RequestBody @Valid CreateEventDto createPost);
}
