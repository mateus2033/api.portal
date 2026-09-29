package portal.voll.api.infra.repository.jobopening;

import portal.voll.api.domain.enums.jobopening.JobOpeningLevel;
import portal.voll.api.domain.enums.jobopening.JobOpeningType;

import java.time.LocalDate;

public interface JobOpeningListProjection {
        Long getJobId();
        String getCode();
        String getName();
        JobOpeningType getType();
        JobOpeningLevel getLevel();
        Integer getApplicationLimit();
        LocalDate getPublicationDate();
        LocalDate getDueDate();
        Boolean getActive();
        String getDescription();
        Long getEnterpriseId();
        Long getUserId();
}
