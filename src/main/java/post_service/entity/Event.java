package post_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "event")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Setter
@Getter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "owner_id", nullable = false)
    private long ownerId;

    @Column(name = "description", nullable = false, length = 4096)
    private String description;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @Column(name = "address", nullable = false, length = 256)
    private String address;

    @Column(name = "city", nullable = false, length = 128)
    private String city;

    @Column(name = "country_id", nullable = false)
    private long countryId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "event", orphanRemoval = true)
    private List<Participant> participants;
}
