package bssm.team15.hackaton.application.game;

import bssm.team15.hackaton.domain.game.Game;
import bssm.team15.hackaton.domain.game.GameStatus;
import bssm.team15.hackaton.infrastructure.persistence.game.GameRepository;
import bssm.team15.hackaton.presentation.user.dto.response.GameResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetGameResultUseCase {

    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    public GameResultResponse result(Long id){
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 id 의 게임을 찾을 수 없습니다"));

        validate(game);

        return GameResultResponse.from(game);
    }

    private void validate(Game game){
        if(game.getStatus() != GameStatus.FINISHED){
            throw new IllegalArgumentException("게임이 아직 진행중입니다.");
        }
    }

}
