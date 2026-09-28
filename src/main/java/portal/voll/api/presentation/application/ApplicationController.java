package portal.voll.api.presentation.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portal.voll.api.domain.enums.response.ResponseJson;
import portal.voll.api.domain.services.application.MyApplications;
import portal.voll.api.infra.repository.application.ApplicationInterfaceRepository;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    final MyApplications myApplications;

    public ApplicationController(MyApplications myApplications) {
        this.myApplications = myApplications;
    }

    @GetMapping(value = "myapplications")
    public ResponseEntity<ResponseJson<Page<ApplicationResponse>>> listApplication(@PageableDefault(size = 10, page = 0, sort = "name") Pageable pageable) {
        Page<ApplicationInterfaceRepository> applications = myApplications.execute(pageable);
        Page<ApplicationResponse> response = ApplicationAssembler.toResponsePage(applications);
        return ResponseEntity.ok(
                new ResponseJson<>(true, HttpStatus.OK.value(), response)
        );
    }
}
