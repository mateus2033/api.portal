package portal.voll.api.presentation.auth;

import portal.voll.api.domain.enums.user.UserType;

public record UserResponse(
        Long id,
        String name,
        String email,
        UserType type
) {
}
