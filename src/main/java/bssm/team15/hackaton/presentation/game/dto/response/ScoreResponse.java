package bssm.team15.hackaton.presentation.game.dto.response;

import bssm.team15.hackaton.domain.game.Game;

import java.util.List;

public record ScoreResponse(
        List<TeamScoreResponse> teams
) {
    public static ScoreResponse from(Game game){
        return new ScoreResponse(
                game.getGameRoom().getTeams()
                        .stream()
                        .map(TeamScoreResponse::from)
                        .toList()
        );
    }
}
