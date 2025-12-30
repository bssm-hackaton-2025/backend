package bssm.team15.hackaton.presentation.reservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestCreateRequest {

    private String businessRegistrationNumber;
    private String businessName;
    private String ownerName;
    private String location;
    private String experienceName;
    private String description;
    private Long price;
    private Integer maxParticipants;

}
