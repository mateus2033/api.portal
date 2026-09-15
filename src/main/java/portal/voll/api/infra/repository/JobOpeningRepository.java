package portal.voll.api.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portal.voll.api.domain.entities.JobOpening;

public interface JobOpeningRepository extends JpaRepository<JobOpening, Long> {
}
