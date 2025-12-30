package bssm.team15.hackaton.domain.game;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Game {

    private static final int GAME_DURATION_MINUTES = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private GameRoom gameRoom;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public static Game start(GameRoom room) {
        Game game = new Game();
        game.gameRoom = room;
        game.status = GameStatus.PLAYING;
        game.startTime = LocalDateTime.now();
        game.endTime = game.startTime.plusMinutes(GAME_DURATION_MINUTES);
        room.attachGame(game);
        return game;
    }

    public boolean isFinished() {
        return LocalDateTime.now().isAfter(endTime);
    }

    public void finish(){
        if (this.status == GameStatus.FINISHED) {
            return;
        }
        status = GameStatus.FINISHED;
    }

    public Team getWinnerTeam() {
        List<Team> teams = gameRoom.getTeams();

        Team teamA = teams.get(0);
        Team teamB = teams.get(1);

        if (teamA.getScore() > teamB.getScore()) return teamA;
        if (teamB.getScore() > teamA.getScore()) return teamB;

        return null;
    }
}
