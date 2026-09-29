package portal.voll.api.presentation.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import portal.voll.api.domain.enums.response.ResponseJson;
import portal.voll.api.domain.services.application.MyApplicationService;
import portal.voll.api.domain.services.application.RemoveApplicationService;
import portal.voll.api.infra.repository.application.ApplicationMyApplicationProjection;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    final MyApplicationService myApplicationService;
    final RemoveApplicationService removeApplicationService;

    public ApplicationController(
            MyApplicationService myApplicationService,
            RemoveApplicationService removeApplicationService
    ) {
        this.myApplicationService = myApplicationService;
        this.removeApplicationService = removeApplicationService;
    }

    @GetMapping(value = "myapplications")
    public ResponseEntity<ResponseJson<Page<ApplicationResponse>>> listApplication(@PageableDefault(size = 10, page = 0, sort = "name") Pageable pageable) {
        Page<ApplicationMyApplicationProjection> applications = myApplicationService.execute(pageable);
        Page<ApplicationResponse> response = ApplicationAssembler.toResponsePage(applications);
        return ResponseEntity.ok(
                new ResponseJson<>(true, HttpStatus.OK.value(), response)
        );
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseJson<String>> remove(@PathVariable Long id) {

        String response = this.removeApplicationService.execute(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseJson<>(true, HttpStatus.OK.value(), response));
    }
}
