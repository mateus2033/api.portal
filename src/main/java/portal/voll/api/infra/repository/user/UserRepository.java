package portal.voll.api.infra.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import portal.voll.api.domain.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String username);
    //Boolean getByEmail(String email);
}
