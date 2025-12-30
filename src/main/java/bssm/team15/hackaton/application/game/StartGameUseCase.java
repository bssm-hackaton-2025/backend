package bssm.team15.hackaton.application.game;

import bssm.team15.hackaton.domain.game.Game;
import bssm.team15.hackaton.domain.game.GameRoom;
import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.infrastructure.persistence.game.GameRepository;
import bssm.team15.hackaton.infrastructure.persistence.game.GameRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StartGameUseCase {

    private final GameRoomRepository gameRoomRepository;
    private final GameRepository gameRepository;

    @Transactional
    public void startGame(Long roomId, User user) {
        GameRoom room = gameRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("방 없음"));

        if (!room.isHost(user)) {
            throw new IllegalStateException("방장만 시작할 수 있습니다.");
        }

        Game game = Game.start(room);

        gameRepository.save(game);
    }

}
