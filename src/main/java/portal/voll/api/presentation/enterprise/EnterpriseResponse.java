package portal.voll.api.presentation.enterprise;

import java.sql.Blob;

public record EnterpriseResponse(
        Long id,
        String name,
        String cnpj,
        String logo
) {
}
