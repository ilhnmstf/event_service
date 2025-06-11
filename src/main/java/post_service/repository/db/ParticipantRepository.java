package post_service.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import post_service.entity.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @Query(nativeQuery = true,
            value = "SELECT EXISTS(SELECT 1 FROM participant WHERE event_id = :eventId AND user_id = :userId)")
    boolean isSubscribe(@Param("eventId") long eventId, @Param("userId") long userId);


    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM participant WHERE event_id = :eventId AND user_id = :userId")
    void deleteByEventIdAndUserId(@Param("eventId") long eventId, @Param("userId") long userId);
}
