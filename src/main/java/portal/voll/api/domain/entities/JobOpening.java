package portal.voll.api.domain.entities;

import lombok.*;
import jakarta.persistence.*;
import portal.voll.api.domain.enums.jobopening.JobOpeningLevel;
import portal.voll.api.domain.enums.jobopening.JobOpeningType;

import java.time.LocalDate;
import java.util.Date;
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
    private String code;
    private JobOpeningType type;
    private JobOpeningLevel level;
    private Boolean active;
    private String description;

    @Column(name = "application_limit")
    private Integer applicationLimit;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    public JobOpening(
            String code,
            JobOpeningType type,
            JobOpeningLevel level,
            Integer applicationLimit,
            LocalDate publicationDate,
            LocalDate dueDate,
            Boolean active,
            String description,
            Enterprise enterprise
    ) {
        this.code = code;
        this.type = type;
        this.level = level;
        this.applicationLimit = applicationLimit;
        this.publicationDate = publicationDate;
        this.dueDate = dueDate;
        this.active = active;
        this.description = description;
        this.enterprise = enterprise;
    }

    @OneToMany(mappedBy = "jobOpening")
    @ToString.Exclude
    private List<Application> applicationList;

    @OneToOne(mappedBy = "jobOpening", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id", nullable = false)
    @ToString.Exclude
    private Enterprise enterprise;
}
