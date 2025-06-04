package post_service.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateEventDto {
    private long ownerId;
    private String description;
    private String address;
    private String city;
    private long countryId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
}
