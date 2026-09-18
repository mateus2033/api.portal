package portal.voll.api.presentation.jobopening;

import portal.voll.api.domain.entities.Address;

public class AddressAssembler {
    public static AddressResponse toResponse(Address address) {
        return new AddressResponse(
                address.getCity(),
                address.getStreet(),
                address.getNumber(),
                address.getPostalCode()
        );
    }
}
