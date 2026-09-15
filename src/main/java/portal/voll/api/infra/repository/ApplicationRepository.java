package portal.voll.api.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portal.voll.api.domain.entities.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
