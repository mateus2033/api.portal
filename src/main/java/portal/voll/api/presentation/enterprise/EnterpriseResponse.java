package portal.voll.api.presentation.enterprise;

public record EnterpriseResponse(
        Long id,
        String name,
        String cnpj,
        String logo
){}
