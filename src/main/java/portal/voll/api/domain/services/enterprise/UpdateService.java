package portal.voll.api.domain.services.enterprise;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import portal.voll.api.domain.entities.Enterprise;
import portal.voll.api.domain.entities.User;
import portal.voll.api.domain.services.archives.ConvertFileToBaseService;
import portal.voll.api.domain.services.user.MeService;
import portal.voll.api.infra.exception.EntityAlreadyExistsException;
import portal.voll.api.infra.repository.EnterpriseRepository;

@Service
public class UpdateService {

    final MeService me;
    final ConvertFileToBaseService convertFileToBase;
    final EnterpriseRepository enterpriseRepository;

    public UpdateService (
            MeService me,
            ConvertFileToBaseService convertFileToBase,
            EnterpriseRepository enterpriseRepository
    ) {
        this.me = me;
        this.convertFileToBase = convertFileToBase;
        this.enterpriseRepository = enterpriseRepository;
    }

    @Transactional
    public Enterprise execute(Long id, String name, String cnpj, MultipartFile logo) {

        User userAuth = me.execute();

        Enterprise enterprise =  enterpriseRepository.findByIdAndUserId(
                id,
                userAuth.getId()
        );

        if(enterprise == null) {
            throw new EntityNotFoundException("Empresa não encontrada.");
        }

        Enterprise existingEnterprise = enterpriseRepository.findByCnpj(cnpj);

        if (enterpriseRepository.findByCnpjExcludingId(cnpj, enterprise.getId()).isPresent()) {
            throw new EntityAlreadyExistsException("CNPJ já existe.");
        }

        var file = convertFileToBase.execute(logo);

        enterprise.setName(name);
        enterprise.setCnpj(cnpj);
        enterprise.setLogo(file);

        return enterpriseRepository.save(enterprise);
    }
}
