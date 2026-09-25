package portal.voll.api.domain.services.enterprise;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import portal.voll.api.domain.entities.Enterprise;
import portal.voll.api.domain.entities.User;
import portal.voll.api.domain.util.ConvertFileToBaseService;
import portal.voll.api.domain.services.user.MeService;
import portal.voll.api.infra.exception.EntityAlreadyExistsException;
import portal.voll.api.infra.repository.enterprise.EnterpriseRepository;

@Service
public class CreateEnterpriseService {

    final MeService me;
    final ConvertFileToBaseService convertFileToBase;
    final EnterpriseRepository enterpriseRepository;

    public CreateEnterpriseService(
        MeService me,
        ConvertFileToBaseService convertFileToBase,
        EnterpriseRepository enterpriseRepository
    ) {
        this.me = me;
        this.convertFileToBase = convertFileToBase;
        this.enterpriseRepository = enterpriseRepository;
    }

    @Transactional
    public Enterprise execute(String name, String cnpj, MultipartFile logo) {

        Enterprise existingEnterprise = enterpriseRepository.findByCnpj(cnpj);

        if(existingEnterprise != null) {
            throw new EntityAlreadyExistsException("Cnpj já cadastrado.");
        }

        User user = me.execute();
        var file = convertFileToBase.execute(logo);

        Enterprise enterprise = new Enterprise(
                name,
                cnpj,
                file,
                user
        );

       return enterpriseRepository.save(enterprise);
    }
}
