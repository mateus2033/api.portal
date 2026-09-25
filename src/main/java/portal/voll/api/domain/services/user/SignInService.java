package portal.voll.api.domain.services.user;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import portal.voll.api.domain.entities.User;
import portal.voll.api.infra.repository.user.UserRepository;
import portal.voll.api.infra.security.TokenService;

@Service
public class SignInService {

    final AuthenticationManager manager;
    final TokenService tokenService;
    final UserRepository repository;

    public SignInService(
            AuthenticationManager manager,
            TokenService tokenService,
            UserRepository repository
    ) {
        this.manager = manager;
        this.tokenService = tokenService;
        this.repository = repository;
    }

    public String execute(String email, String password) {
         var authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
         var authentication = manager.authenticate(authenticationToken);
         return tokenService.generateToken((User) authentication.getPrincipal());
    }
}
