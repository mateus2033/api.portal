package portal.voll.api.domain.services.jobopening;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import portal.voll.api.domain.entities.JobOpening;
import portal.voll.api.domain.services.user.MeService;
import portal.voll.api.infra.repository.jobopening.JobOpeningListProjection;
import portal.voll.api.infra.repository.jobopening.JobOpeningRepository;

@Service
public class ListJobsEnterpriseService {

    final MeService me;
    final JobOpeningRepository jobOpeningRepository;

    public ListJobsEnterpriseService(
            MeService me,
            JobOpeningRepository jobOpeningRepository
    ) {
        this.me = me;
        this.jobOpeningRepository = jobOpeningRepository;
    }

    public Page<JobOpeningListProjection> execute(Pageable pageable, Long enterpriseId) {

        var userId = me
                .execute()
                .getId();

        return this.jobOpeningRepository.listJobOpening(
                userId,
                enterpriseId,
                pageable
        );
    }
}
