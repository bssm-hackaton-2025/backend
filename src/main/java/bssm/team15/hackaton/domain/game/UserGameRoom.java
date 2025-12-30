package bssm.team15.hackaton.domain.game;

import bssm.team15.hackaton.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserGameRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private GameRoom gameRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    @Builder
    UserGameRoom(User user, GameRoom gameRoom, Team team) {
        this.user = user;
        this.gameRoom = gameRoom;
        this.team = team;
    }

    public static UserGameRoom from(User user, GameRoom room, Team team) {
        return UserGameRoom.builder()
                .user(user)
                .gameRoom(room)
                .team(team)
                .build();
    }

}

