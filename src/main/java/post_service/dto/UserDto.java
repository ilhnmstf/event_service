package post_service.dto;

import java.util.List;

public class UserDto {
    private long id;
    private List<Long> followeeIds;
    private List<Long> followerIds;
}
