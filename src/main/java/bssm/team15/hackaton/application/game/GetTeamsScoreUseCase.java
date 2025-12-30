package bssm.team15.hackaton.application.game;

import bssm.team15.hackaton.domain.game.Game;
import bssm.team15.hackaton.infrastructure.persistence.game.GameRepository;
import bssm.team15.hackaton.presentation.game.dto.response.ScoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetTeamsScoreUseCase {

    private final GameRepository gameRepository;

    public ScoreResponse getTeamsScore(Long gameId){
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("게임을 찾을 수 없습니다."));

        return ScoreResponse.from(game);
    }

}
