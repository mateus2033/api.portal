package portal.voll.api.presentation.jobopening;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import portal.voll.api.domain.entities.JobOpening;
import portal.voll.api.domain.enums.response.ResponseJson;
import portal.voll.api.domain.services.jobopening.CreateJobOpeningService;
import portal.voll.api.domain.valueobjects.jobopening.RegisterJobOpening;
import java.net.URI;

@RestController
@RequestMapping("/jobs")
public class JobOpeningController {

    final CreateJobOpeningService createService;

    public JobOpeningController(CreateJobOpeningService createService) {
        this.createService = createService;
    }

    @PostMapping
    public ResponseEntity<ResponseJson<JobOpeningResponse>> register(@RequestBody @Valid RegisterJobOpening data, UriComponentsBuilder uriBuilder) {
        JobOpening jobOpening = createService.execute(data);
        URI location = uriBuilder.path("/users/{id}").buildAndExpand(jobOpening.getId()).toUri();
        JobOpeningResponse response = JobOpeningAssembler.toResponse(jobOpening);
        return ResponseEntity.created(location).body(
                new ResponseJson<>(true, HttpStatus.CREATED.value(), response)
        );
    }
}
