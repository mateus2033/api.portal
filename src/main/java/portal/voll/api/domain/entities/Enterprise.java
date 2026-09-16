package portal.voll.api.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Enterprise")
@Table(name = "enterprises")
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cnpj;

    @Lob
    @Column(name = "logo", columnDefinition = "LONGTEXT") // ou LONGTEXT no MySQL
    private String logo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "enterprise")
    @ToString.Exclude
    private List<JobOpening> jobOpeningList;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate created_at;

    @UpdateTimestamp
    private LocalDate updated_at;

    public Enterprise(String name, String cnpj, String logo, User user) {
        this.name = name;
        this.cnpj = cnpj;
        this.logo = logo;
        this.user = user;
    }
}
