package portal.voll.api.domain.services.jobopening;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portal.voll.api.domain.entities.Address;
import portal.voll.api.domain.entities.Enterprise;
import portal.voll.api.domain.entities.JobOpening;
import portal.voll.api.domain.entities.User;
import portal.voll.api.domain.services.user.MeService;
import portal.voll.api.domain.valueobjects.address.RegisterAddress;
import portal.voll.api.domain.valueobjects.jobopening.RegisterJobOpening;
import portal.voll.api.infra.repository.AddressRepository;
import portal.voll.api.infra.repository.EnterpriseRepository;
import portal.voll.api.infra.repository.JobOpeningRepository;

import java.time.LocalDate;

@Service
public class CreateJobOpeningService {

    final MeService me;
    final GenerateJobRandomCode generateRandomCode;
    final EnterpriseRepository enterpriseRepository;
    final JobOpeningRepository jobOpeningRepository;
    final AddressRepository addressRepository;

    public CreateJobOpeningService(
          MeService me,
          GenerateJobRandomCode generateRandomCode,
          EnterpriseRepository enterpriseRepository,
          JobOpeningRepository jobOpeningRepository,
          AddressRepository addressRepository
    ){
        this.me = me;
        this.generateRandomCode = generateRandomCode;
        this.enterpriseRepository = enterpriseRepository;
        this.jobOpeningRepository = jobOpeningRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public JobOpening execute(RegisterJobOpening data) {

        User userAuth = me.execute();

        Enterprise enterprise =  enterpriseRepository.findByIdAndUserId(
                data.enterpriseId(),
                userAuth.getId()
        );

        if(enterprise == null) {
            throw new EntityNotFoundException("Empresa não encontrada.");
        }

        String code = generateRandomCode.execute();
        LocalDate publicationDate = LocalDate.now();
        LocalDate dueDate = publicationDate.plusDays(data.applicationLimit());

        JobOpening jobOpening = new JobOpening(
                code,
                data.type(),
                data.level(),
                data.applicationLimit(),
                publicationDate,
                dueDate,
                true,
                data.description(),
                enterprise
        );

        JobOpening newJobOpening = jobOpeningRepository.save(jobOpening);
        RegisterAddress newAddress = data.address();

        Address address = new Address(
                newAddress.city(),
                newAddress.street(),
                newAddress.number(),
                newAddress.postalCode(),
                newJobOpening
        );

        addressRepository.save(address);
        newJobOpening.setAddress(address);
        return newJobOpening;
    }
}
