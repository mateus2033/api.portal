package portal.voll.api.infra.repository.application;

import java.time.LocalDate;

public interface ApplicationMyApplicationProjection {
        Long getId();
        String getUser();
        String getEnterprise();
        String getJobCode();
        String getJobName();
        String getJobType();
        String getJobLevel();
        LocalDate getApplicationDate();
        String getJobDescription();
}
