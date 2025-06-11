package post_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import post_service.dto.CreateEventDto;
import post_service.dto.EventDto;
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
    @Mapping(target = "participantIds", source = "participants", qualifiedByName = "getParticipantIds")
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

    @Named("getParticipantIds")
    default List<Long> getParticipantIds(List<Participant> participants) {
        return participants == null ? List.of() : participants.stream()
                .map(Participant::getUserId)
                .collect(Collectors.toList());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "startAt", source = "startAt", qualifiedByName = "toDateTime")
    @Mapping(target = "endAt", source = "endAt", qualifiedByName = "toDateTime")
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "participants", ignore = true)
    Event update(@MappingTarget Event event, CreateEventDto updatedEvent);
}
