package bssm.team15.hackaton.presentation.trash;

import bssm.team15.hackaton.application.trash.CreateTrashUseCase;
import bssm.team15.hackaton.application.trash.ReadTrashesUseCase;
import bssm.team15.hackaton.application.trash.SetStatusTrashUseCase;
import bssm.team15.hackaton.application.trash.UpdateTrashUseCase;
import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.presentation.trash.dto.request.TrashRejectRequest;
import bssm.team15.hackaton.presentation.trash.dto.response.TrashResponse;
import bssm.team15.hackaton.shared.annotation.Admin;
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
    private final UpdateTrashUseCase updateTrashUseCase;
    private final SetStatusTrashUseCase setStatusTrashUseCase;

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

    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @AuthenticationPrincipal User user,
            @PathVariable Long id,
            @RequestParam("imageData") MultipartFile imageData,
            @RequestParam("location") String location
    ) {
        updateTrashUseCase.update(user, id, imageData, location);
    }

    @Admin
    @PatchMapping("/{id}/approval")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(
            @AuthenticationPrincipal User user,
            @PathVariable Long id
    ) {
        setStatusTrashUseCase.confirm(user, id);
    }

    @PatchMapping("/{id}/rejection")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reject(
            @AuthenticationPrincipal User user,
            @PathVariable Long id,
            @RequestBody TrashRejectRequest request
    ) {
        setStatusTrashUseCase.reject(user, id, request.getReason());
    }

}
