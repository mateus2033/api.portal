package portal.voll.api.domain.services.user;

import jakarta.persistence.EntityNotFoundException;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portal.voll.api.domain.entities.Application;
import portal.voll.api.domain.entities.JobOpening;
import portal.voll.api.domain.entities.User;
import portal.voll.api.domain.util.ConvertFileToBaseService;
import portal.voll.api.domain.valueobjects.user.ApplicateJob;
import portal.voll.api.infra.exception.EntityAlreadyExistsException;
import portal.voll.api.infra.repository.application.ApplicationRepository;
import portal.voll.api.infra.repository.jobopening.JobOpeningRepository;

@Service
public class ApplicateJobService {

    final MeService me;
    final ConvertFileToBaseService convertFileToBaseService;
    final ApplicationRepository applicationRepository;
    final JobOpeningRepository jobOpeningRepository;

    public  ApplicateJobService(
            MeService me,
            ConvertFileToBaseService convertFileToBaseService,
            ApplicationRepository applicationRepository,
            JobOpeningRepository jobOpeningRepository
    ) {
        this.me = me;
        this.convertFileToBaseService = convertFileToBaseService;
        this.applicationRepository = applicationRepository;
        this.jobOpeningRepository = jobOpeningRepository;
    }

    @Transactional
    public String execute(@NonNull ApplicateJob data) {

        User user = me.execute();

        JobOpening jobOpening = jobOpeningRepository
                .findById(data.job_opening_id())
                .orElseThrow(() -> new EntityNotFoundException("Oportunidade não encontrada."));

        Boolean existingApplication = applicationRepository.existsByUserAndJobOpening(user, jobOpening);
        if (existingApplication) {
            throw new EntityAlreadyExistsException("Candidatura já foi aplicada para essa vaga.");
        }

        String curriculumFile = convertFileToBaseService.execute(data.curriculum());
        Application application = new Application(
                curriculumFile,
                user,
                jobOpening
        );

        applicationRepository.save(application);
        return "Candidatura feita com sucesso.";
    }
}
