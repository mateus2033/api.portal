package portal.voll.api.presentation.jobopening;

import org.springframework.data.domain.Page;
import portal.voll.api.domain.entities.JobOpening;
import java.util.List;

public class JobOpeningAssembler {

    public static List<JobOpeningResponse> toResponseList(List<JobOpening> jobOpenings) {
        return jobOpenings.stream()
                .map(JobOpeningAssembler::toResponse)
                .toList();
    }

    public static Page<JobOpeningResponse> toResponsePage(Page<JobOpening> jobOpenings) {
        return jobOpenings.map(JobOpeningAssembler::toResponse);
    }

    public static JobOpeningResponse toResponse(JobOpening jobOpening) {
        return new JobOpeningResponse(
                jobOpening.getCode(),
                jobOpening.getName(),
                jobOpening.getType(),
                jobOpening.getLevel(),
                jobOpening.getPublicationDate(),
                jobOpening.getDueDate(),
                jobOpening.getDescription(),
                AddressAssembler.toResponse(jobOpening.getAddress())
        );
    }
}
