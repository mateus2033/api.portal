package portal.voll.api.domain.valueobjects.user;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;
import portal.voll.api.domain.anotation.ValidFile;

public record ApplicateJob(

        @NotNull
        Long job_opening_id,

        @NotNull
        @ValidFile
        MultipartFile curriculum
){}