package portal.voll.api.infra.exception;

public class UserAlreadyExistsException  extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
