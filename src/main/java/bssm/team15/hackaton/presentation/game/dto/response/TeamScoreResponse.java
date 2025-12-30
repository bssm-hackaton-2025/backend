package bssm.team15.hackaton.presentation.game.dto.response;

import bssm.team15.hackaton.domain.game.Team;

public record TeamScoreResponse(
        Long id,
        String name,
        Integer score
) {
    public static TeamScoreResponse from(Team team) {
        return new TeamScoreResponse(
                team.getId(),
                team.getName(),
                team.getScore()
        );
    }
}
