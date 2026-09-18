package portal.voll.api.presentation.jobopening;

import portal.voll.api.domain.enums.jobopening.JobOpeningLevel;
import portal.voll.api.domain.enums.jobopening.JobOpeningType;
import java.time.LocalDate;

public record JobOpeningResponse(
        String code,
        JobOpeningType type,
        JobOpeningLevel level,
        LocalDate publication_date,
        LocalDate due_date,
        String description,
        AddressResponse address
){}
