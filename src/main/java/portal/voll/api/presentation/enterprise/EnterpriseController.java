package portal.voll.api.presentation.enterprise;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import portal.voll.api.domain.entities.Enterprise;
import portal.voll.api.domain.enums.response.ResponseJson;
import portal.voll.api.domain.services.enterprise.CreateEnterpriseService;
import portal.voll.api.domain.services.enterprise.UpdateEnterpriseService;
import portal.voll.api.domain.valueobjects.enterprise.RegisterEnterprise;
import portal.voll.api.domain.valueobjects.enterprise.UpdateEnterprise;
import java.net.URI;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    final CreateEnterpriseService createService;
    final UpdateEnterpriseService updateEnterpriseService;

    public EnterpriseController(
            CreateEnterpriseService createService,
            UpdateEnterpriseService updateEnterpriseService
    ) {
        this.createService = createService;
        this.updateEnterpriseService = updateEnterpriseService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseJson<EnterpriseResponse>> register(@ModelAttribute @Valid RegisterEnterprise data, UriComponentsBuilder uriBuilder) {

        Enterprise enterprise = createService.execute(
                data.name(),
                data.cnpj(),
                data.logo()
        );

        URI location = uriBuilder.path("/users/{id}").buildAndExpand(enterprise.getId()).toUri();
        EnterpriseResponse response = EnterpriseAssembler.toResponse(enterprise);
        return ResponseEntity.created(location).body(
                new ResponseJson<>(true, HttpStatus.CREATED.value(), response)
        );
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseJson<EnterpriseResponse>> update(@ModelAttribute @Valid UpdateEnterprise data) {

        Enterprise enterprise = updateEnterpriseService.execute(
                data.id(),
                data.name(),
                data.cnpj(),
                data.logo()
        );

        EnterpriseResponse response = EnterpriseAssembler.toResponse(enterprise);
        return ResponseEntity.ok().body(
                new ResponseJson<>(true, HttpStatus.OK.value(), response)
        );
    }
}
