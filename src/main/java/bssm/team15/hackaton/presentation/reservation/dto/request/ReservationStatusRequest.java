package bssm.team15.hackaton.presentation.reservation.dto.request;

import bssm.team15.hackaton.domain.reservation.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationStatusRequest {
    private ReservationStatus status;
}
