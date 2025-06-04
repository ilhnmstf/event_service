package post_service.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post_service.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
