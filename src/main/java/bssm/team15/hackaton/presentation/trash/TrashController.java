package bssm.team15.hackaton.presentation.trash;

import bssm.team15.hackaton.application.trash.CreateTrashUseCase;
import bssm.team15.hackaton.application.trash.ReadTrashesUseCase;
import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.presentation.trash.dto.response.TrashResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trashes")
public class TrashController {
    private final CreateTrashUseCase createTrashUseCase;
    private final ReadTrashesUseCase readTrashesUseCase;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @AuthenticationPrincipal User user,
            @RequestParam("imageData") MultipartFile imageData,
            @RequestParam("location") String location
    ) {
        createTrashUseCase.create(user, imageData, location);
    }

    @GetMapping
    public List<TrashResponse> readTrashes(
            @AuthenticationPrincipal User user
    ) {
        return readTrashesUseCase.readTrashes(user);
    }

}
