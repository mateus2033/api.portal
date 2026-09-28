package portal.voll.api.domain.services.jobopening;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portal.voll.api.infra.repository.jobopening.JobOpeningRepository;

import java.time.LocalDate;

@Service
public class DeactivateJobOpeningService {

    final JobOpeningRepository jobOpeningRepository;

    public DeactivateJobOpeningService(JobOpeningRepository jobOpeningRepository) {
        this.jobOpeningRepository = jobOpeningRepository;
    }

    @Transactional
    public void execute() {
        this.jobOpeningRepository.deactivate(LocalDate.now());
    }
}
