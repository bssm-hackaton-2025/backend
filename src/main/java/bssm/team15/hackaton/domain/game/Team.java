package bssm.team15.hackaton.domain.game;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private GameRoom gameRoom;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<UserGameRoom> members = new ArrayList<>();

    private Integer maxMembers = 2;

    @Builder
    Team(String name, GameRoom gameRoom) {
        this.name = name;
        this.gameRoom = gameRoom;
    }

    public static Team from(String name, GameRoom gameRoom) {
        return Team.builder()
                .name(name)
                .gameRoom(gameRoom)
                .build();
    }

    public boolean isFull() {
        return members.size() >= this.maxMembers;
    }

}
