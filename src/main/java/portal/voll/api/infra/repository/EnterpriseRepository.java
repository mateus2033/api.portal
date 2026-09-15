package portal.voll.api.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portal.voll.api.domain.entities.Enterprise;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}
