package portal.voll.api.infra.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;
import portal.voll.api.domain.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
