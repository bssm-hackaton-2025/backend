package bssm.team15.hackaton.presentation.reservation.dto.response;

import bssm.team15.hackaton.domain.reservation.ReservationRequest;
import bssm.team15.hackaton.domain.reservation.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservationResponse {

    private Long id;
    private Long ownerId;
    private String businessRegistrationNumber;
    private String businessName;
    private String ownerName;
    private String location;
    private String experienceName;
    private String description;
    private Long price;
    private Integer maxParticipants;
    private ReservationStatus status;

    public static ReservationResponse from(ReservationRequest request) {
        return new ReservationResponse(
                request.getId(),
                request.getOwnerId(),
                request.getBusinessRegistrationNumber(),
                request.getBusinessName(),
                request.getOwnerName(),
                request.getLocation(),
                request.getExperienceName(),
                request.getDescription(),
                request.getPrice(),
                request.getMaxParticipants(),
                request.getStatus()
        );
    }
}
