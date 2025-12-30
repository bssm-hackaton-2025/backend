package bssm.team15.hackaton.infrastructure.persistence.reservation;

import bssm.team15.hackaton.domain.reservation.ReservationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {
    List<ReservationRequest> findAllByOwnerId(Long ownerId);
}
