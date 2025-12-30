package bssm.team15.hackaton.presentation.reservation.dto.response;

import bssm.team15.hackaton.domain.experience.Experience;

public record SimpleExperienceResponse(
        Long id,
        String title,
        String businessName,
        String ownerName,
        String description,
        String location
) {
    public static SimpleExperienceResponse from(Experience experience) {
        return new SimpleExperienceResponse(
                experience.getId(),
                experience.getTitle(),
                experience.getBusinessName(),
                experience.getOwner().getNickname(),
                experience.getDescription(),
                experience.getLocation()
        );
    }
}
