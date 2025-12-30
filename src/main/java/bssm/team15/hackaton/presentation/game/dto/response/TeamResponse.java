package bssm.team15.hackaton.presentation.game.dto.response;

import bssm.team15.hackaton.domain.game.Team;

import java.util.List;

public record TeamResponse(
        String teamName,
        Integer maxMembers,
        List<String> users
) {
    public static TeamResponse from(Team team) {
        return new TeamResponse(
                team.getName(),
                team.getMaxMembers(),
                team.getMembers().stream()
                        .map(ugr -> ugr.getUser().getNickname())
                        .toList()
        );
    }
}

