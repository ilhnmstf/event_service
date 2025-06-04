package post_service.dto.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDto {
    private long ownerId;
    private String description;
    private String address;
    private String city;
    private long countryId;
    private String startAt;
    private String endAt;
    private String createdAt;
    private String updatedAt;
    private List<Long> userIds;
}
