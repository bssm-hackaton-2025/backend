package bssm.team15.hackaton.application.experience;

import bssm.team15.hackaton.domain.reservation.Reservation;
import bssm.team15.hackaton.domain.reservation.ReservationStatus;
import bssm.team15.hackaton.infrastructure.persistence.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
public class ReservationUseCase {

    private final ReservationRepository reservationRepository;

    @Transactional
    public void changeStatus(Long reservationId, ReservationStatus status) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("예약 없음"));
        reservation.updateStatus(status);
    }

    public List<Reservation> getMyReservations(Long userId) {
        return reservationRepository.findAllByUserId(userId);
    }
}
