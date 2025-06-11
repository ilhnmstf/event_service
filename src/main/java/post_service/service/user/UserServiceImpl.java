package post_service.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import post_service.client.UserServiceClientV1;
import post_service.repository.cache.UserCacheRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserServiceClientV1 userServiceClientV1;
    private final UserCacheRepository userCacheRepository;

    @Override
    public void validateUserById(long userId) {
        userCacheRepository.get(userId)
                .orElseGet(() -> userServiceClientV1.getById(userId));
    }
}
