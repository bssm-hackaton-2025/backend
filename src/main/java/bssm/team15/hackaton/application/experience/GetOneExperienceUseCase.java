package bssm.team15.hackaton.application.experience;

import bssm.team15.hackaton.infrastructure.persistence.reservation.ExperienceRepository;
import bssm.team15.hackaton.presentation.reservation.dto.response.ExperienceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetOneExperienceUseCase {

    private final ExperienceRepository experienceRepository;

    public ExperienceResponse getExperience(Long id){
        return ExperienceResponse.from(
                experienceRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("체험을 찾을 수 없습니다."))
        );
    }

}
