package portal.voll.api.presentation.application;

import org.springframework.data.domain.Page;
import portal.voll.api.infra.repository.application.ApplicationMyApplicationProjection;

public class ApplicationAssembler {

    public static ApplicationResponse toResponseMyApplications(ApplicationMyApplicationProjection p) {
        return new ApplicationResponse(
                p.getId(),
                p.getUser(),
                p.getEnterprise(),
                p.getJobCode(),
                p.getJobName(),
                p.getJobType(),
                p.getJobLevel(),
                p.getApplicationDate(),
                p.getJobDescription()
        );
    }

    public static Page<ApplicationResponse> toResponsePage(Page<ApplicationMyApplicationProjection> page) {
        return page.map(ApplicationAssembler::toResponseMyApplications);
    }
}
