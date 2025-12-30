package bssm.team15.hackaton.infrastructure.persistence.game;

import bssm.team15.hackaton.domain.game.Game;
import bssm.team15.hackaton.domain.game.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByStatus(GameStatus status);
}
