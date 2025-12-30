package bssm.team15.hackaton.presentation.game;

import bssm.team15.hackaton.application.game.CreateGameRoomUseCase;
import bssm.team15.hackaton.application.game.GetGameRoomInfoUseCase;
import bssm.team15.hackaton.application.game.JoinRoomUseCase;
import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.presentation.game.dto.request.CreateGameRoomRequest;
import bssm.team15.hackaton.presentation.game.dto.request.JoinGameRoomRequest;
import bssm.team15.hackaton.presentation.game.dto.response.GameRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/games")
public class GameController {

    private final CreateGameRoomUseCase createGameRoomUseCase;
    private final JoinRoomUseCase joinRoomUseCase;
    private final GetGameRoomInfoUseCase getGameRoomInfoUseCase;

    @PostMapping("/rooms")
    public GameRoomResponse createGameRoom(
            @AuthenticationPrincipal User user,
            @RequestBody CreateGameRoomRequest createGameRoomRequest
    ){
        return createGameRoomUseCase.execute(createGameRoomRequest, user);
    }

    @PatchMapping("/rooms/{id}")
    public GameRoomResponse joinGameRoom(
            @PathVariable Long id,
            @AuthenticationPrincipal User user,
            @RequestBody JoinGameRoomRequest request
    ){
        return joinRoomUseCase.join(id, user, request);
    }

    @GetMapping("/rooms/{id}")
    public GameRoomResponse getGameRoomInfo(
            @PathVariable Long id
    ){
        return getGameRoomInfoUseCase.getGameRoomInfo(id);
    }

}
