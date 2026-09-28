package portal.voll.api.infra.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import portal.voll.api.domain.services.jobopening.DeactivateJobOpeningService;

@Component
public class JobOpeningExpirationSchedule {

    final DeactivateJobOpeningService deactivateJobOpeningService;

    public JobOpeningExpirationSchedule(DeactivateJobOpeningService deactivateJobOpeningService) {
        this.deactivateJobOpeningService = deactivateJobOpeningService;
    }

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void execute() {
        this.deactivateJobOpeningService.execute();
    }
}
