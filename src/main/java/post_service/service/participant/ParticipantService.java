package post_service.service.participant;


import post_service.entity.Event;

public interface ParticipantService {

    void save(Event event, long userId);

    void delete(Event event, long userId);
}
