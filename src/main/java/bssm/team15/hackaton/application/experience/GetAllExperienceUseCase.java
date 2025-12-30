package bssm.team15.hackaton.application.experience;

import bssm.team15.hackaton.infrastructure.persistence.reservation.ExperienceRepository;
import bssm.team15.hackaton.presentation.reservation.dto.response.ExperienceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllExperienceUseCase {

    private final ExperienceRepository experienceRepository;

    public List<ExperienceResponse> getExperiences() {
        return experienceRepository.findAll()
                .stream()
                .map(ExperienceResponse::from)
                .toList();
    }

}
