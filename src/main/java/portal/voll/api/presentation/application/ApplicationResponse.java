package portal.voll.api.presentation.application;

public record ApplicationResponse(
        Long id,
        String user,
        String enterprise,
        String jobCode,
        String jobName,
        String jobType,
        String jobLevel,
        String jobDescription
){}
