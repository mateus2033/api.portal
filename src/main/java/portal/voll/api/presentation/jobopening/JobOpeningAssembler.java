package portal.voll.api.presentation.jobopening;

import org.springframework.data.domain.Page;
import portal.voll.api.domain.entities.JobOpening;
import portal.voll.api.infra.repository.jobopening.JobOpeningListProjection;

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
                jobOpening.getId(),
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

    public static Page<JobOpeningResponse> toResponsePageFromProjection(Page<JobOpeningListProjection> page) {
        return page.map(JobOpeningAssembler::toResponseFromProjection);
    }

    public static JobOpeningResponse toResponseFromProjection(JobOpeningListProjection jobOpening) {
        return new JobOpeningResponse(
                jobOpening.getJobId(),
                jobOpening.getCode(),
                jobOpening.getName(),
                jobOpening.getType(),
                jobOpening.getLevel(),
                jobOpening.getPublicationDate(),
                jobOpening.getDueDate(),
                jobOpening.getDescription(),
                null
        );
    }
}
