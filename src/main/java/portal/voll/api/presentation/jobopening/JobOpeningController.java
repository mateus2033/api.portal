package portal.voll.api.presentation.jobopening;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import portal.voll.api.domain.entities.JobOpening;
import portal.voll.api.domain.enums.response.ResponseJson;
import portal.voll.api.domain.services.jobopening.CreateJobOpeningService;
import portal.voll.api.domain.services.jobopening.ListJobsEnterpriseService;
import portal.voll.api.domain.services.jobopening.RemoveJobOpeningService;
import portal.voll.api.domain.services.jobopening.SearchJobOpeningService;
import portal.voll.api.domain.valueobjects.jobopening.RegisterJobOpening;
import portal.voll.api.infra.repository.jobopening.JobOpeningListProjection;

import java.net.URI;

@RestController
@RequestMapping("/jobs")
public class JobOpeningController {

    final CreateJobOpeningService createService;
    final SearchJobOpeningService searchJobOpeningService;
    final RemoveJobOpeningService removeJobOpeningService;
    final ListJobsEnterpriseService listJobsEnterpriseService;

    public JobOpeningController(
            CreateJobOpeningService createService,
            SearchJobOpeningService searchJobOpeningService,
            RemoveJobOpeningService removeJobOpeningService,
            ListJobsEnterpriseService listJobsEnterpriseService
    ) {
        this.createService = createService;
        this.searchJobOpeningService = searchJobOpeningService;
        this.removeJobOpeningService = removeJobOpeningService;
        this.listJobsEnterpriseService = listJobsEnterpriseService;
    }

    @GetMapping
    public ResponseEntity<ResponseJson<Page<JobOpeningResponse>>> search(
            @PageableDefault(size = 10, page = 0, sort = "name")
            Pageable pageable,
            @RequestParam String search
    ) {
        Page<JobOpening> jobOpening = searchJobOpeningService.execute(
                search,
                pageable
        );

        Page<JobOpeningResponse> response = JobOpeningAssembler.toResponsePage(jobOpening);
        return ResponseEntity.ok(
                new ResponseJson<>(true, HttpStatus.OK.value(), response)
        );
    }

    @PostMapping
    @Transactional
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

    @DeleteMapping("/{jobId}/enterprise/{enterpriseId}")
    @Transactional
    public ResponseEntity<ResponseJson<String>> remove(
            @PathVariable Long jobId,
            @PathVariable Long enterpriseId
    ) {
        String response = removeJobOpeningService.execute(
                jobId,
                enterpriseId
        );

        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(new ResponseJson<>(true, HttpStatus.OK.value(), response));
    }

    @GetMapping("/enterprise/{enterpriseId}/list")
    public ResponseEntity<ResponseJson<Page<JobOpeningResponse>>> listJobsEnterprise(
            @PageableDefault(size = 10, page = 0, sort = "name")
            Pageable pageable,
            @PathVariable Long enterpriseId
    ) {
        Page<JobOpeningListProjection> jobOpening = this.listJobsEnterpriseService.execute(
                pageable,
                enterpriseId
        );

        Page<JobOpeningResponse> response = JobOpeningAssembler.toResponsePageFromProjection(jobOpening);
        return ResponseEntity.ok(
                new ResponseJson<>(true, HttpStatus.OK.value(), response)
        );
    }
}
