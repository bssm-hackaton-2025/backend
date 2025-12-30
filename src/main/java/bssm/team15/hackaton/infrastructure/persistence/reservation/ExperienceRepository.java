package bssm.team15.hackaton.infrastructure.persistence.reservation;

import bssm.team15.hackaton.domain.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}