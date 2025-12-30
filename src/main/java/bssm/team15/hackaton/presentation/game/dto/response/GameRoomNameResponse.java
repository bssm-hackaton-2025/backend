package bssm.team15.hackaton.presentation.game.dto.response;

import bssm.team15.hackaton.domain.game.GameRoom;

public record GameRoomNameResponse(
        Long id,
        String title,
        Boolean isPrivate
) {
    public static GameRoomNameResponse from(GameRoom gameRoom) {
        return new GameRoomNameResponse(
                gameRoom.getId(),
                gameRoom.getTitle(),
                gameRoom.isPrivate()
        );
    }
}
