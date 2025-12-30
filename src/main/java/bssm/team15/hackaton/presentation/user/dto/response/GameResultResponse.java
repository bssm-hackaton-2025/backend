package bssm.team15.hackaton.presentation.user.dto.response;

import bssm.team15.hackaton.domain.game.Game;
import bssm.team15.hackaton.domain.game.Team;

public record GameResultResponse(
        Long gameId,
        int teamAScore,
        int teamBScore,
        String winnerTeamName
) {
    public static GameResultResponse from(Game game) {
        Team a = game.getGameRoom().getTeams().get(0);
        Team b = game.getGameRoom().getTeams().get(1);

        Team winner = game.getWinnerTeam();

        return new GameResultResponse(
                game.getId(),
                a.getScore(),
                b.getScore(),
                winner != null ? winner.getName() : "DRAW"
        );
    }
}

