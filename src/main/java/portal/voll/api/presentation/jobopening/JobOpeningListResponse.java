package portal.voll.api.presentation.jobopening;

import java.time.LocalDate;

public record JobOpeningListResponse(
        Long id,
        String code,
        String name,
        String type,
        String level,
        Integer applicationLimit,
        LocalDate publicationDate,
        LocalDate dueDate,
        Boolean active,
        String description
){}
