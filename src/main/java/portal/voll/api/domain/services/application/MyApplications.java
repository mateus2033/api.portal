package portal.voll.api.domain.services.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import portal.voll.api.domain.services.user.MeService;
import portal.voll.api.infra.repository.application.ApplicationInterfaceRepository;
import portal.voll.api.infra.repository.application.ApplicationRepository;

@Service
public class MyApplications {

    final MeService meService;
    final ApplicationRepository applicationRepository;

    public MyApplications(
            MeService meService,
            ApplicationRepository applicationRepository
    ) {
        this.meService = meService;
        this.applicationRepository = applicationRepository;
    }

    public Page<ApplicationInterfaceRepository> execute(Pageable pageable) {
        return applicationRepository.myApplications(
                meService.execute().getId(),
                pageable
        );
    }
}
