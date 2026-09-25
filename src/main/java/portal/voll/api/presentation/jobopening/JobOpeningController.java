package portal.voll.api.presentation.jobopening;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import portal.voll.api.domain.entities.JobOpening;
import portal.voll.api.domain.enums.response.ResponseJson;
import portal.voll.api.domain.services.jobopening.CreateJobOpeningService;
import portal.voll.api.domain.services.jobopening.SearchJobOpening;
import portal.voll.api.domain.valueobjects.jobopening.RegisterJobOpening;
import java.net.URI;

@RestController
@RequestMapping("/jobs")
public class JobOpeningController {

    final CreateJobOpeningService createService;
    final SearchJobOpening searchJobOpening;

    public JobOpeningController(
            CreateJobOpeningService createService,
            SearchJobOpening searchJobOpening
    ) {
        this.createService = createService;
        this.searchJobOpening = searchJobOpening;
    }

    @GetMapping
    public ResponseEntity<ResponseJson<Page<JobOpeningResponse>>> search(
            @PageableDefault(size = 10, page = 0, sort = "name")
            Pageable pageable,
            @RequestParam String search
    ) {
        Page<JobOpening> jobOpening = searchJobOpening.execute(search, pageable);
        Page<JobOpeningResponse> response = JobOpeningAssembler.toResponsePage(jobOpening);
        return ResponseEntity.ok(
                new ResponseJson<>(true, HttpStatus.OK.value(), response)
        );
    }

    @PostMapping
    public ResponseEntity<ResponseJson<JobOpeningResponse>> register(
            @RequestBody @Valid RegisterJobOpening data,
            UriComponentsBuilder uriBuilder
    ) {
        JobOpening jobOpening = createService.execute(data);
        URI location = uriBuilder.path("/users/{id}").buildAndExpand(jobOpening.getId()).toUri();
        JobOpeningResponse response = JobOpeningAssembler.toResponse(jobOpening);
        return ResponseEntity.created(location).body(
                new ResponseJson<>(true, HttpStatus.CREATED.value(), response)
        );
    }
}
