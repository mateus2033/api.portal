package portal.voll.api.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Blob;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Application")
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "curriculum", columnDefinition = "LONGTEXT")
    private String curriculum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_opening_id", nullable = false)
    @ToString.Exclude
    private JobOpening jobOpening;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate created_at;

    @UpdateTimestamp
    private LocalDate updated_at;

    public Application(
            String curriculum,
            User user,
            JobOpening jobOpening
    ) {
            this.curriculum = curriculum;
            this.user = user;
            this.jobOpening =  jobOpening;
    }
}
