package post_service.controller.event;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import post_service.dto.event.CreateEventDto;
import post_service.dto.event.EventDto;
import post_service.service.post.EventService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
public class EventControllerImpl implements EventController {
    private final EventService eventService;

    @PostMapping
    @Override
    public ResponseEntity<EventDto> create(CreateEventDto createPost) {
        return ResponseEntity.ok().body(eventService.create(createPost));
    }
}
