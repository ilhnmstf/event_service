package post_service.repository.cache;

import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import post_service.dto.EventDto;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventRedisOptimisticOperation {
    private final RedisTemplate<String, EventDto> eventRedisTemplate;

    @Retryable(
            retryFor = { OptimisticLockException.class },
            maxAttempts = 5,
            backoff = @Backoff(value = 100, multiplier = 2))
    public Boolean saveOptimistic(String key, Runnable doing) {
        log.debug("Try to update value with key {}", key);
        return eventRedisTemplate.execute(new SessionCallback<>() {
            @Override
            public Boolean execute(RedisOperations operations) throws DataAccessException {

                log.debug("Monitoring the changes in the key {}", key);
                operations.watch(key);
                log.debug("Start redis transaction");
                operations.multi();
                doing.run();
                Object res = operations.exec();
                log.debug("Finish redis transaction");
                return res != null;
            }
        });
    }
}
