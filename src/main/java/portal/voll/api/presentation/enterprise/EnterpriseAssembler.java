package portal.voll.api.presentation.enterprise;

import portal.voll.api.domain.entities.Enterprise;

public class EnterpriseAssembler {

    private EnterpriseAssembler() {}

    public static EnterpriseResponse toResponse(Enterprise enterprise) {
        return new EnterpriseResponse(
                enterprise.getId(),
                enterprise.getName(),
                enterprise.getCnpj(),
                enterprise.getLogo()
        );
    }
}
