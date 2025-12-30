package bssm.team15.hackaton.infrastructure.persistence.game;

import bssm.team15.hackaton.domain.game.GameRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRoomRepository extends JpaRepository<GameRoom, Long> {
}
