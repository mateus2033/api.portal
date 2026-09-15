package portal.voll.api.presentation.auth;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import portal.voll.api.domain.entities.User;
import portal.voll.api.domain.enums.response.ResponseJson;
import portal.voll.api.domain.services.user.SignInService;
import portal.voll.api.domain.services.user.SignUpService;
import portal.voll.api.domain.valueobjects.auth.*;
import portal.voll.api.infra.security.DataTokenJWT;

import java.net.URI;

@RestController
@RequestMapping("/sessao")
public class AuthController {

    final SignUpService signUpService;
    final SignInService signInService;

    public AuthController(
            SignUpService signUpService,
            SignInService signInService
    ) {
        this.signUpService = signUpService;
        this.signInService = signInService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<DataTokenJWT> authentication(@RequestBody @Valid Auth data) {
         String token = signInService.execute(data.email(), data.password());
         return ResponseEntity.ok(new DataTokenJWT(token));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseJson<UserResponse>> register(@RequestBody @Valid Register data, UriComponentsBuilder uriBuilder) {
        User user = signUpService.execute(data);
        URI location = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        UserResponse response = UserAssembler.toResponse(user);
        return ResponseEntity.created(location).body(
                new ResponseJson<>(true, HttpStatus.CREATED.value(), response)
        );
    }
}
