package post_service.service.participant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import post_service.entity.Event;
import post_service.entity.Participant;
import post_service.repository.db.ParticipantRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepository participantRepository;

    @Override
    public void save(Event event, long userId) {
        validate(event.getId(), userId, true);
        participantRepository.save(new Participant().setEvent(event).setUserId(userId));
        log.debug("User with id {} subscribe on event with id {}", userId, event.getId());
    }

    @Override
    public void delete(Event event, long userId) {
        validate(event.getId(), userId, false);
        participantRepository.deleteByEventIdAndUserId(event.getId(), userId);
        log.debug("User with id {} unsubscribe on event with id {}", userId, event.getId());
    }

    private void validate(long eventId, long userId, boolean isSubscribe) {
        String answer = isSubscribe ? " already " : " not ";
        if (participantRepository.isSubscribe(eventId, userId) == isSubscribe) {
            throw new IllegalArgumentException("User with id " + userId + answer + "subscribe on event with id " + eventId);
        }
    }
}
