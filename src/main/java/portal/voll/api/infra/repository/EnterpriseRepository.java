package portal.voll.api.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import portal.voll.api.domain.entities.Enterprise;

import java.util.Optional;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

    Enterprise findByid(Long id);

    Enterprise findByCnpj(String cnpj);

    Enterprise findByIdAndUserId(Long id, Long user_id);

    @Query("SELECT e FROM Enterprise e WHERE e.cnpj = :cnpj AND e.id <> :id")
    Optional<Enterprise> findByCnpjExcludingId(@Param("cnpj") String cnpj, @Param("id") Long id);
}
