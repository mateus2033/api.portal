package portal.voll.api.domain.valueobjects.enterprise;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.multipart.MultipartFile;


public record RegisterEnterprise(
        @NotBlank
        String name,

        @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", message = "CNPJ inválido")
        String cnpj,

        MultipartFile logo
) {}