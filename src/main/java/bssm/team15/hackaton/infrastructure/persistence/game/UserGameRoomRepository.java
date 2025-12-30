package bssm.team15.hackaton.infrastructure.persistence.game;

import bssm.team15.hackaton.domain.game.UserGameRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGameRoomRepository extends JpaRepository<UserGameRoom, Long> {
}
