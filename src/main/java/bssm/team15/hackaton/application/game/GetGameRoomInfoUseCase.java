package bssm.team15.hackaton.application.game;

import bssm.team15.hackaton.infrastructure.persistence.game.GameRoomRepository;
import bssm.team15.hackaton.presentation.game.dto.response.GameRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetGameRoomInfoUseCase {

    private final GameRoomRepository gameRoomRepository;

    public GameRoomResponse getGameRoomInfo(Long id){
        return GameRoomResponse.from(
                gameRoomRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("해당하는 번호의 방이 없습니다."))
        );
    }
}
