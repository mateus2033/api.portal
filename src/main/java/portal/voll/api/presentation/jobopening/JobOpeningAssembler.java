package portal.voll.api.presentation.jobopening;

import portal.voll.api.domain.entities.JobOpening;

public class JobOpeningAssembler {

    public  JobOpeningAssembler() {}

    public static JobOpeningResponse toResponse(JobOpening jobOpening) {
        return new JobOpeningResponse(
                jobOpening.getCode(),
                jobOpening.getType(),
                jobOpening.getLevel(),
                jobOpening.getPublicationDate(),
                jobOpening.getDueDate(),
                jobOpening.getDescription(),
                AddressAssembler.toResponse(jobOpening.getAddress())
        );
    }
}
