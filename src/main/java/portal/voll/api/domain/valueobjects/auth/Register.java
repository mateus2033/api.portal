package portal.voll.api.domain.valueobjects.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Register(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String password
){}
