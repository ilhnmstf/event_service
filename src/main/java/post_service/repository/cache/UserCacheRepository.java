package post_service.repository.cache;

import post_service.dto.UserDto;

import java.util.Optional;

public interface UserCacheRepository {

    Optional<UserDto> get(long userId);
}
