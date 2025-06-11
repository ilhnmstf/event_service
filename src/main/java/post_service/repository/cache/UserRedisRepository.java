package post_service.repository.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import post_service.dto.UserDto;

import java.util.Optional;
// todo add spring stater for dto
@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRedisRepository implements UserCacheRepository {
    private final HashOperations<String, Long, UserDto> userHashOperations;
    private final String key;

    @Autowired
    public UserRedisRepository(RedisTemplate<String, UserDto> userRedisTemplate) {
        userHashOperations = userRedisTemplate.opsForHash();
        key = "user";
    }

    @Override
    public Optional<UserDto> get(long userId) {
        UserDto user = userHashOperations.get(key, userId);
        log.info("Get value {} in the redis on key {}:{}", user, key, userId);
        return Optional.ofNullable(user);
    }
}
