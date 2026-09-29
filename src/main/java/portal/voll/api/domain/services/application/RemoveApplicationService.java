package portal.voll.api.domain.services.application;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import portal.voll.api.domain.entities.Application;
import portal.voll.api.domain.services.user.MeService;
import portal.voll.api.infra.repository.application.ApplicationRepository;

@Service
public class RemoveUserApplication {

    final MeService meService;
    final ApplicationRepository applicationRepository;

    public RemoveUserApplication(
            MeService meService,
            ApplicationRepository applicationRepository
    ) {
        this.meService = meService;
        this.applicationRepository = applicationRepository;
    }

    public String execute(Long id) {
        var userId = meService.execute().getId();

        Application application = applicationRepository.getApplicationByIdAndUserId(
                id,
                userId
        );

        if (application == null) {
            throw new EntityNotFoundException("Candidatura não encontrada.");
        }

        this.applicationRepository.delete(application);
        return "Candidatura removida";
    }
}
