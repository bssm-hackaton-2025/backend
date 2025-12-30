package bssm.team15.hackaton.application.game;

import bssm.team15.hackaton.domain.game.GameRoom;
import bssm.team15.hackaton.domain.game.Team;
import bssm.team15.hackaton.domain.game.UserGameRoom;
import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.infrastructure.persistence.game.GameRoomRepository;
import bssm.team15.hackaton.infrastructure.persistence.game.UserGameRoomRepository;
import bssm.team15.hackaton.presentation.game.dto.request.JoinGameRoomRequest;
import bssm.team15.hackaton.presentation.game.dto.response.GameRoomResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinRoomUseCase {

    private final GameRoomRepository gameRoomRepository;
    private final UserGameRoomRepository userGameRoomRepository;

    @Transactional
    public GameRoomResponse join(Long roomId, User user, JoinGameRoomRequest request) {
        GameRoom room = gameRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("방 없음"));

        if (room.isPrivate()) {
            if (request.getPassword() == null ||
                    !room.getPassword().equals(request.getPassword())) {
                throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
            }
        }

        Team team = room.chooseTeam();


        UserGameRoom userGameRoom = UserGameRoom.from(user, room, team);

        team.getMembers().add(userGameRoom);

        userGameRoomRepository.save(userGameRoom);

        return GameRoomResponse.from(room);
    }
}

