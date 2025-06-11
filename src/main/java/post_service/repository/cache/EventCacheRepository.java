package post_service.repository.cache;

import post_service.dto.EventDto;

import java.util.Optional;

public interface EventCacheRepository {
    EventDto saveOptimistic(EventDto save);

    Optional<EventDto> get(long eventId);

    void delete(long eventId);
}
