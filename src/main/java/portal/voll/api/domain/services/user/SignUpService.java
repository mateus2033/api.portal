package portal.voll.api.domain.services.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import portal.voll.api.domain.entities.User;
import portal.voll.api.domain.valueobjects.auth.Register;
import portal.voll.api.infra.exception.EntityAlreadyExistsException;
import portal.voll.api.infra.repository.UserRepository;

@Service
public class SignUpService {

    final UserRepository repository;

    public SignUpService(UserRepository repository) {
        this.repository = repository;
    }

    public User execute(Register data) {

        UserDetails existingUser = repository.findByEmail(data.email());

        if (existingUser != null) {
            throw new EntityAlreadyExistsException("Email já cadastrado");
        }

        User user = new User(data);
        user.setPassword(new BCryptPasswordEncoder().encode(data.password()));

        return repository.save(user);
    }
}
