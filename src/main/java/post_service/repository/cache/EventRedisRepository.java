package post_service.repository.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import post_service.dto.EventDto;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Repository
@Slf4j
public class EventRedisRepository implements EventCacheRepository {
    private final EventRedisOptimisticOperation eventRedisOptimisticOperation;
    private final HashOperations<String, Long, EventDto> eventHashOperations;
    private final ExecutorService saveEventPool;
    private final String key;

    @Autowired
    public EventRedisRepository(EventRedisOptimisticOperation eventRedisOptimisticOperation,
                                RedisTemplate<String, EventDto> eventRedisTemplate, ExecutorService saveEventPool) {
        this.eventRedisOptimisticOperation = eventRedisOptimisticOperation;
        eventHashOperations = eventRedisTemplate.opsForHash();
        this.saveEventPool = saveEventPool;
        key = "event";
    }

    @Override
    public EventDto saveOptimistic(EventDto event) {
        CompletableFuture.runAsync(() ->
                eventRedisOptimisticOperation.saveOptimistic(key + ":" + event.getId(), () -> save(event)),
                saveEventPool);
        return event;
    }

    @Override
    public Optional<EventDto> get(long eventId) {
        EventDto event = eventHashOperations.get(key, eventId);
        log.debug("Get value {} in key {}:{} in redis", event, key, eventId);
        return Optional.ofNullable(event);
    }

    @Override
    public void delete(long eventId) {
        log.debug("Delete value in key {}:{} in redis", key, eventId);
        eventHashOperations.delete(key, eventId);
    }

    private void save(EventDto event) {
        eventHashOperations.put(key, event.getId(), event);
        log.debug("Save value {} in redis on key {}:{}", event, key, event.getId());
    }
}
