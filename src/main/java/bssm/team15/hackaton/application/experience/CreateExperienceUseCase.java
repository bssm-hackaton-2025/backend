package bssm.team15.hackaton.application.experience;

import bssm.team15.hackaton.domain.experience.Experience;
import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.infrastructure.persistence.reservation.ExperienceRepository;
import bssm.team15.hackaton.infrastructure.persistence.user.UserRepository;
import bssm.team15.hackaton.presentation.reservation.dto.request.CreateExperienceRequest;
import bssm.team15.hackaton.presentation.reservation.dto.response.ExperienceResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class CreateExperienceUseCase {

    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;

    @Transactional
    public ExperienceResponse create(User user, CreateExperienceRequest request){
        User owner = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다"));
        Experience experience = experienceRepository.save(
                new Experience(
                        request.getTitle(),
                        request.getLocation(),
                        request.getDescription(),
                        request.getPrice(),
                        request.getMaxParticipants(),
                        request.getBusinessRegistrationNumber(),
                        request.getBusinessName(),
                        owner
                )
        );
        return ExperienceResponse.from(experience);
    }

}
