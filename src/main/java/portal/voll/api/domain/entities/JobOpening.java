package portal.voll.api.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "JobOpening")
@Table(name = "jobs_openings")
public class JobOpening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "jobOpening")
    @ToString.Exclude
    private List<Application> applicationList;

    @OneToMany(mappedBy = "jobOpening")
    @ToString.Exclude
    private List<Address> addressList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id", nullable = false)
    @ToString.Exclude
    private Enterprise enterprise;
}
