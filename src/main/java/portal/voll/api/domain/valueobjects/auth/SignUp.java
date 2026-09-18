package portal.voll.api.domain.valueobjects.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import portal.voll.api.domain.enums.user.UserType;

public record SignUp(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotNull
        UserType type,

        @NotBlank
        String password
){}
