package bssm.team15.hackaton.presentation.game.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateGameRoomRequest {

    private String title;

    private Boolean isPrivate;

    private String password;

}
