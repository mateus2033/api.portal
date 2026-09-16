package portal.voll.api.domain.services.archives;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import portal.voll.api.infra.exception.FileConversionException;

import java.io.IOException;
import java.util.Base64;

@Service
public class ConvertFileToBaseService {

    public String execute(MultipartFile file) {

        try {
            byte[] archive = file.getBytes();
            return Base64.getEncoder().encodeToString(archive);
        } catch (IOException e) {
            throw new FileConversionException("Erro ao converter arquivo para Base64");
        }
    }
}
