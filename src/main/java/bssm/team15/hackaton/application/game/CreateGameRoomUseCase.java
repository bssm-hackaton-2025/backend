package bssm.team15.hackaton.application.game;

import bssm.team15.hackaton.domain.game.GameRoom;
import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.infrastructure.persistence.game.GameRoomRepository;
import bssm.team15.hackaton.presentation.game.dto.request.CreateGameRoomRequest;
import bssm.team15.hackaton.presentation.game.dto.response.GameRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateGameRoomUseCase {

    private final GameRoomRepository gameRoomRepository;

    public GameRoomResponse execute(CreateGameRoomRequest request, User user) {

        GameRoom gameRoom = GameRoom.from(
                request.getTitle(),
                request.getIsPrivate(),
                request.getPassword(),
                user
        );

        GameRoom saved = gameRoomRepository.save(gameRoom);

        return GameRoomResponse.from(saved);
    }
}
