package bssm.team15.hackaton.application.notification;

import bssm.team15.hackaton.domain.reservation.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationNotification(
        Long reservationId,
        Long ownerId,
        ReservationStatus status,
        String type,
        LocalDateTime sentAt
) {
}
