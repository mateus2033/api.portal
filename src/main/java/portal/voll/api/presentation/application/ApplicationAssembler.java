package portal.voll.api.presentation.application;

import org.springframework.data.domain.Page;
import portal.voll.api.infra.repository.application.ApplicationInterfaceRepository;

public class ApplicationAssembler {

    public static ApplicationResponse toResponseMyApplications(ApplicationInterfaceRepository p) {
        return new ApplicationResponse(
                p.getId(),
                p.getUser(),
                p.getEnterprise(),
                p.getJobCode(),
                p.getJobName(),
                p.getJobType(),
                p.getJobLevel(),
                p.getJobDescription()
        );
    }

    public static Page<ApplicationResponse> toResponsePage(Page<ApplicationInterfaceRepository> page) {
        return page.map(ApplicationAssembler::toResponseMyApplications);
    }
}
