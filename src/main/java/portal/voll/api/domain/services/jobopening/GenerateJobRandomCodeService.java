package portal.voll.api.domain.services.jobopening;

import org.springframework.stereotype.Service;
import portal.voll.api.infra.repository.jobopening.JobOpeningRepository;

import java.util.Locale;
import java.util.UUID;

@Service
public class GenerateJobRandomCodeService {

    private String code;
    final JobOpeningRepository jobOpeningRepository;

    public GenerateJobRandomCodeService(JobOpeningRepository jobOpeningRepository) {
        this.jobOpeningRepository = jobOpeningRepository;
    }

    public String execute() {
        String code;
        do {
             code = UUID.randomUUID()
                     .toString()
                     .substring(0,8)
                     .toUpperCase(Locale.ROOT);

        }while (jobOpeningRepository.existsByCode(code));
        return code;
    }
}
