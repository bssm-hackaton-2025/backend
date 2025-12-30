package bssm.team15.hackaton.domain.game;

import bssm.team15.hackaton.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private boolean isPrivate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User host;

    @OneToOne(mappedBy = "gameRoom", cascade = CascadeType.ALL)
    private Game game;

    private String password;

    @OneToMany(mappedBy = "gameRoom", cascade = CascadeType.ALL)
    private List<Team> teams = new ArrayList<>();

    private GameRoom(String title, boolean isPrivate, String password, User host) {
        this.title = title;
        this.isPrivate = isPrivate;
        this.password = isPrivate ? password : null;
        this.host = host;

        Team teamA = Team.from("A", this);
        Team teamB = Team.from("B", this);

        this.teams.add(teamA);
        this.teams.add(teamB);

        UserGameRoom hostJoin = UserGameRoom.from(host, this, teamA);
        teamA.getMembers().add(hostJoin);
    }

    public static GameRoom from(String title, boolean isPrivate, String password, User host) {
        return new GameRoom(title, isPrivate, password, host);
    }

    public boolean isHost(User user) {
        return this.host.getId().equals(user.getId());
    }

    public Team chooseTeam() {
        Team teamA = teams.get(0);
        Team teamB = teams.get(1);

        if (teamA.isFull() && teamB.isFull()) {
            throw new IllegalStateException("방이 가득 찼습니다.");
        }

        if (teamA.isFull()) return teamB;
        if (teamB.isFull()) return teamA;

        if (teamA.getMembers().size() == teamB.getMembers().size()) {
            return Math.random() < 0.5 ? teamA : teamB;
        }

        return teamA.getMembers().size() < teamB.getMembers().size()
                ? teamA
                : teamB;
    }

    public void attachGame(Game game) {
        this.game = game;
    }

}
