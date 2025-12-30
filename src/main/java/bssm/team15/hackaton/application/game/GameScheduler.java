package bssm.team15.hackaton.application.game;

import bssm.team15.hackaton.domain.game.Game;
import bssm.team15.hackaton.domain.game.GameStatus;
import bssm.team15.hackaton.infrastructure.persistence.game.GameRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GameScheduler {

    private final GameRepository gameRepository;
    private final GameSseManager gameSseManager;
    private final GetGameResultUseCase getGameResultUseCase;
    private final GetTeamsScoreUseCase getTeamsScoreUseCase;

    @Scheduled(fixedRate = 5000)
    @Transactional
    public void finishExpiredGames() {
        List<Game> playingGames = gameRepository.findByStatus(GameStatus.PLAYING);

        for (Game game : playingGames) {
            if (game.isFinished()) {
                game.finish();
                gameSseManager.send(game.getId(), getGameResultUseCase.result(game.getId()), "gameResult");
            }
            gameSseManager.send(game.getId(), getTeamsScoreUseCase.getTeamsScore(game.getId()), "teamsScore");
        }
    }

}
