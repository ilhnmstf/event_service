package post_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateEventDto {

    private long ownerId;

    @NotBlank(message = "should not be null or empty")
    private String description;

    @NotBlank(message = "should not be null or empty")
    private String address;

    @NotBlank(message = "should not be null or empty")
    private String city;

    private long countryId;

    private LocalDateTime startAt;

    private LocalDateTime endAt;
}