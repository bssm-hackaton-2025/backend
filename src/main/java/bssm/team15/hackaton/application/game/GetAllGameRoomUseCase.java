package bssm.team15.hackaton.application.game;

import bssm.team15.hackaton.domain.game.GameRoom;
import bssm.team15.hackaton.infrastructure.persistence.game.GameRoomRepository;
import bssm.team15.hackaton.presentation.game.dto.response.GameRoomNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllGameRoomUseCase {

    private final GameRoomRepository gameRoomRepository;

    public List<GameRoomNameResponse> getAllGameRooms() {
        List<GameRoom> gameRooms = gameRoomRepository.findAll();

        return gameRooms.stream()
                .map(GameRoomNameResponse::from)
                .toList();
    }
}
