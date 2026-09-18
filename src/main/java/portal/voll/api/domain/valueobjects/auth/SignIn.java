package portal.voll.api.domain.valueobjects.auth;

import jakarta.validation.constraints.NotBlank;

public record SignIn(
        @NotBlank
        String email,
        @NotBlank
        String password
) { }
