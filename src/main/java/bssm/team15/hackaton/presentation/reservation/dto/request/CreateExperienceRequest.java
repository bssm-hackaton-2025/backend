package bssm.team15.hackaton.presentation.reservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateExperienceRequest {

    private String title;

    private String location;

    private String description;

    private Long price;

    private Integer maxParticipants;

    private String businessRegistrationNumber;

    private String businessName;
}
