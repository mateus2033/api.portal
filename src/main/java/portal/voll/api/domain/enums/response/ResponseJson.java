package portal.voll.api.domain.enums.response;

public record ResponseJson<T>(
        boolean status,
        int code,
        T data
) { }
