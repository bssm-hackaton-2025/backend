package bssm.team15.hackaton.infrastructure.persistence.reservation;

import bssm.team15.hackaton.domain.reservation.Reservation;
import bssm.team15.hackaton.domain.reservation.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // List를 위해 반드시 필요!

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    long countByExperienceIdAndStatusIn(Long experienceId, List<ReservationStatus> statuses);

    List<Reservation> findAllByUserId(Long userId);
}