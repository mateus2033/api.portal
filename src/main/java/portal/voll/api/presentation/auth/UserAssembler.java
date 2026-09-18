package portal.voll.api.presentation.auth;

import portal.voll.api.domain.entities.User;

public class UserAssembler {

    private UserAssembler() {}

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getType()
        );
    }
}
