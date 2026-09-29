package portal.voll.api.domain.services.jobopening;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import portal.voll.api.domain.entities.JobOpening;
import portal.voll.api.infra.repository.jobopening.JobOpeningRepository;

@Service
public class SearchJobOpeningService {

    final JobOpeningRepository jobOpeningRepository;

    public SearchJobOpeningService(JobOpeningRepository jobOpeningRepository) {
        this.jobOpeningRepository = jobOpeningRepository;
    }

    public Page<JobOpening> execute(String search, Pageable pageable) {
        return jobOpeningRepository.search(search, pageable);
    }
}
