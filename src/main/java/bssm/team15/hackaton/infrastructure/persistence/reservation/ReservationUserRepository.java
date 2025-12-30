package bssm.team15.hackaton.infrastructure.persistence.reservation;

import bssm.team15.hackaton.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationUserRepository extends JpaRepository<User, Long> {
}
