package bssm.team15.hackaton.presentation.game.dto.response;

import bssm.team15.hackaton.domain.game.GameRoom;

import java.util.List;

public record GameRoomResponse(
        Long roomId,
        String title,
        boolean isPrivate,
        String hostName,
        List<TeamResponse> teams
) {
    public static GameRoomResponse from(GameRoom room) {
        return new GameRoomResponse(
                room.getId(),
                room.getTitle(),
                room.isPrivate(),
                room.getHost().getNickname(),
                room.getTeams().stream()
                        .map(TeamResponse::from)
                        .toList()
        );
    }
}

