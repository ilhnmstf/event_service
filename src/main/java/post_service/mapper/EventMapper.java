package post_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import post_service.dto.event.CreateEventDto;
import post_service.dto.event.EventDto;
import post_service.entity.Event;
import post_service.entity.Participant;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {LocalDateTimeMapper.class})
public interface EventMapper {

    @Mapping(target = "startAt", source = "startAt", qualifiedByName = "toString")
    @Mapping(target = "endAt", source = "endAt", qualifiedByName = "toString")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "toString")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "toString")
    @Mapping(target = "userIds", source = "participants", qualifiedByName = "getUserIds")
    EventDto toDto(Event event);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "startAt", source = "startAt", qualifiedByName = "toDateTime")
    @Mapping(target = "endAt", source = "endAt", qualifiedByName = "toDateTime")
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "participants", ignore = true)
    Event toEntity(CreateEventDto event);

    @Named("getUserIds")
    default List<Long> getUserIds(List<Participant> participants) {
        return participants.stream()
                .map(Participant::getUserId)
                .collect(Collectors.toList());
    }

}
