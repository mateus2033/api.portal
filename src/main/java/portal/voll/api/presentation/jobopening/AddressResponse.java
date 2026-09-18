package portal.voll.api.presentation.jobopening;

public record AddressResponse(
        String city,
        String street,
        String number,
        String postalCode
) { }
