package domain.event;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name="events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @Column(nullable=false) // this column cannot be null
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(nullable=false) // this column cannot be null
    private String title;
    @Column (nullable = false)
    private String details;

    @Column(nullable=false, unique = true)
    private String slug;

    @Column(nullable=false, name="max_attendees")
    private Integer max_attendees;

   @ManyToOne
   @JoinColumn(name = "event_id", nullable = false)
    private Event event;

   @Column(name = "created_at", nullable = false)
   private LocalDateTime createdAt;

}
