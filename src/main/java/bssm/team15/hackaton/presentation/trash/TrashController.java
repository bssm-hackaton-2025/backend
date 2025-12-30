package bssm.team15.hackaton.presentation.trash;

import bssm.team15.hackaton.application.trash.CreateTrashUseCase;
import bssm.team15.hackaton.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trashes")
public class TrashController {
    private final CreateTrashUseCase createTrashUseCase;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void create(
            @AuthenticationPrincipal User user,
            @RequestParam("imageData") MultipartFile imageData,
            @RequestParam("location") String location
    ) {
        createTrashUseCase.create(user, imageData, location);
    }

}
