package bssm.team15.hackaton.presentation.reservation;

import bssm.team15.hackaton.application.experience.CreateExperienceUseCase;
import bssm.team15.hackaton.application.experience.GetAllExperienceUseCase;
import bssm.team15.hackaton.application.experience.GetOneExperienceUseCase;
import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.presentation.reservation.dto.request.CreateExperienceRequest;
import bssm.team15.hackaton.presentation.reservation.dto.response.ExperienceResponse;
import bssm.team15.hackaton.presentation.reservation.dto.response.SimpleExperienceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/experiences")
public class ExperienceController {

    private final GetAllExperienceUseCase getAllExperienceUseCase;
    private final GetOneExperienceUseCase getOneExperienceUseCase;
    private final CreateExperienceUseCase createExperienceUseCase;

    @GetMapping
    public List<SimpleExperienceResponse> getAllExperience() {
        return getAllExperienceUseCase.getExperiences();
    }

    @GetMapping("/{experienceId}")
    public ExperienceResponse getExperience(
            @PathVariable Long experienceId
    ){
        return getOneExperienceUseCase.getExperience(experienceId);
    }

    @PostMapping
    public ExperienceResponse createExperience(
            @AuthenticationPrincipal User user,
            @RequestBody CreateExperienceRequest request
    ){
        return createExperienceUseCase.create(user, request);
    }
}
