package bssm.team15.hackaton.infrastructure.persistence.reservation;

import bssm.team15.hackaton.domain.experience.Experience;
import bssm.team15.hackaton.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    Optional<Experience> findByOwner(User owner);
}