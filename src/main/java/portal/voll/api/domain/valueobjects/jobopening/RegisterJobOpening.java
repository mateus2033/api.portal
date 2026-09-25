package portal.voll.api.domain.valueobjects.jobopening;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import portal.voll.api.domain.enums.jobopening.JobOpeningLevel;
import portal.voll.api.domain.enums.jobopening.JobOpeningType;
import portal.voll.api.domain.valueobjects.address.RegisterAddress;

public record RegisterJobOpening(

        @NotBlank
        String name,

        @NotNull
        JobOpeningType type,

        @NotNull
        JobOpeningLevel level,

        @NotNull
        @Positive
        Integer applicationLimit,

        @NotBlank
        String description,

        @NotNull
        @Positive
        Long enterpriseId,

        @NotNull
        @Valid
        RegisterAddress address
){}
