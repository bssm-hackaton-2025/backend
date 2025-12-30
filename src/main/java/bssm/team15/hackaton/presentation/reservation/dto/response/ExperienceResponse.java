package bssm.team15.hackaton.presentation.reservation.dto.response;

import bssm.team15.hackaton.domain.experience.Experience;

public record ExperienceResponse(
        Long id,
        String businessName,
        String ownerName,
        String description,
        String location,
        Long price
) {
    public static ExperienceResponse from(Experience experience) {
        return new ExperienceResponse(
                experience.getId(),
                experience.getBusinessName(),
                experience.getOwner().getNickname(),
                experience.getDescription(),
                experience.getLocation(),
                experience.getPrice()
        );
    }
}
