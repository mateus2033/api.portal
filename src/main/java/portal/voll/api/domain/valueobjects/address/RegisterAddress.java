package portal.voll.api.domain.valueobjects.address;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterAddress(
        @NotNull
        String city,

        @NotNull
        String street,

        @NotNull
        String number,

        @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "CEP inválido")
        String postalCode
){}
