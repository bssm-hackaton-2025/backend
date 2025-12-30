package bssm.team15.hackaton.presentation.game;

import bssm.team15.hackaton.application.game.*;
import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.presentation.game.dto.request.CreateGameRoomRequest;
import bssm.team15.hackaton.presentation.game.dto.request.JoinGameRoomRequest;
import bssm.team15.hackaton.presentation.game.dto.request.SubmitScoreRequest;
import bssm.team15.hackaton.presentation.game.dto.response.GameRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RequiredArgsConstructor
@RestController
@RequestMapping("/games")
public class GameController {

    private final CreateGameRoomUseCase createGameRoomUseCase;
    private final JoinRoomUseCase joinRoomUseCase;
    private final GetGameRoomInfoUseCase getGameRoomInfoUseCase;
    private final GameSseManager gameSseManager;
    private final AddScoreUseCase addScoreUseCase;
    private final StartGameUseCase startGameUseCase;

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

    @GetMapping("/{gameId}/subscribe")
    public SseEmitter subscribe(@PathVariable Long gameId) {
        return gameSseManager.subscribe(gameId);
    }

    @PatchMapping("/{teamId}/score")
    public void addScore(
            @RequestBody SubmitScoreRequest request,
            @AuthenticationPrincipal User user,
            @PathVariable Long teamId
    ){
        addScoreUseCase.add(teamId, user, request);
    }

    @PatchMapping("/{roomId}")
    public void gameStart(
            @PathVariable Long roomId,
            @AuthenticationPrincipal User user
    ){
        startGameUseCase.startGame(roomId, user);
    }

}
