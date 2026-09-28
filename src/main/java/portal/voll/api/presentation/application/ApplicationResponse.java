package portal.voll.api.presentation.application;

import java.time.LocalDate;

public record ApplicationResponse(
        Long id,
        String user,
        String enterprise,
        String jobCode,
        String jobName,
        String jobType,
        String jobLevel,
        LocalDate applicationDate,
        String jobDescription
){}
