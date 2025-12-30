package bssm.team15.hackaton.application.game;

import bssm.team15.hackaton.domain.game.Game;
import bssm.team15.hackaton.domain.game.Team;
import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.infrastructure.persistence.game.TeamRepository;
import bssm.team15.hackaton.presentation.game.dto.request.SubmitScoreRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddScoreUseCase {

    private final TeamRepository teamRepository;

    @Transactional
    public void add(Long teamId, User user, SubmitScoreRequest request) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("팀이 존재하지 않습니다."));

        boolean isMember = team.getMembers().stream()
                .anyMatch(member -> member.getUser().getId().equals(user.getId()));

        if (!isMember) {
            throw new IllegalStateException("본인의 팀에만 점수를 추가할 수 있습니다.");
        }

        Game game = team.getGameRoom().getGame();
        if (game.isFinished()) {
            throw new IllegalStateException("이미 종료된 게임입니다.");
        }

        team.addPoint(request.getScore());

    }
}
