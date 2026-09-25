package portal.voll.api.presentation.user;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portal.voll.api.domain.enums.response.ResponseJson;
import portal.voll.api.domain.services.user.ApplicateJobService;
import portal.voll.api.domain.valueobjects.user.ApplicateJob;

@RestController
@RequestMapping("/user")
public class UserController {

    final ApplicateJobService applicateJobService;

    public UserController(ApplicateJobService applicateJobService) {
        this.applicateJobService = applicateJobService;
    }

    @PostMapping(value = "applicatejob", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseJson<String>> register(@ModelAttribute @Valid ApplicateJob data) {

        String response = applicateJobService.execute(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseJson<>(true, HttpStatus.CREATED.value(), response));
    }
}
