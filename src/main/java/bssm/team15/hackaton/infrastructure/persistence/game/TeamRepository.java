package bssm.team15.hackaton.infrastructure.persistence.game;

import bssm.team15.hackaton.domain.game.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
