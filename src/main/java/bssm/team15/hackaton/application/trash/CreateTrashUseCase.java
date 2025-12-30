package bssm.team15.hackaton.application.trash;

import bssm.team15.hackaton.domain.trash.Trash;
import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.infrastructure.persistence.trash.TrashRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class CreateTrashUseCase {

    private final TrashRepository trashRepository;

    @Value("${IMAGE_SAVE_PATH}")
    private String savePath;

    @Transactional
    public void create(User user, MultipartFile imageData, String location) {
        Trash trash = Trash.create(user.getId(), location);
        trashRepository.save(
                trash
        );
        trashRepository.flush();
        try {
            saveImage(trash.getId(), imageData);
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Fail to save image."
            );
        }
    }

    private void saveImage(Long id, MultipartFile imageData) throws IOException {
        if (imageData == null || imageData.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_CONTENT,
                    "Empty file."
            );
        }

        try (InputStream is = imageData.getInputStream()) {
            BufferedImage image = ImageIO.read(is);
            if (image == null) {
                throw new ResponseStatusException(
                        HttpStatus.UNPROCESSABLE_CONTENT,
                        "Wrong file format"
                );
            }
        }

        Path dir = Paths.get(savePath);
        Files.createDirectories(dir);

        Path target = dir.resolve(id + "_1" + ".jpeg");

        try (InputStream is = imageData.getInputStream()) {
            Files.copy(
                    is,
                    target,
                    StandardCopyOption.REPLACE_EXISTING
            );
        }
    }

}
