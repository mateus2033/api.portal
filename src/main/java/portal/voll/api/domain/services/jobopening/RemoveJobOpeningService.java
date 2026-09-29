package portal.voll.api.domain.services.jobopening;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import portal.voll.api.domain.entities.Enterprise;
import portal.voll.api.domain.entities.JobOpening;
import portal.voll.api.domain.services.user.MeService;
import portal.voll.api.infra.repository.enterprise.EnterpriseRepository;
import portal.voll.api.infra.repository.jobopening.JobOpeningRepository;

@Service
public class RemoveJobOpeningService {

    final MeService me;
    final JobOpeningRepository jobOpeningRepository;
    final EnterpriseRepository enterpriseRepository;

    public RemoveJobOpeningService(
            MeService me,
            JobOpeningRepository jobOpeningRepository,
            EnterpriseRepository enterpriseRepository
    ) {
        this.me = me;
        this.jobOpeningRepository = jobOpeningRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    public String execute(Long jobId, Long enterpriseId) {

        var userId = me.execute().getId();

        Enterprise enterprise = this.enterpriseRepository.findByIdAndUserId(
                enterpriseId,
                userId
        );

        if(enterprise == null) {
            throw new EntityNotFoundException("Empresa não encontrada.");
        }

        JobOpening jobOpening = this.jobOpeningRepository.getByIdAndEnterpriseId(
                jobId,
                enterprise.getId()
        );

        if(jobOpening == null) {
            throw new EntityNotFoundException("Vaga não encontrada.");
        }

        this.jobOpeningRepository.delete(jobOpening);
        return "Vaga removida com sucesso.";
    }
}
