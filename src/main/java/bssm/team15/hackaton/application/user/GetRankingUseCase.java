package bssm.team15.hackaton.application.user;

import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.infrastructure.persistence.user.UserRepository;
import bssm.team15.hackaton.presentation.user.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GetRankingUseCase {

    private final UserRepository userRepository;

    public List<UserResponse> getRankingTop5(){
        return userRepository.findAll().stream()
                .sorted(Comparator.comparing(User::getPoint).reversed())
                .limit(5)
                .map(u -> new UserResponse(u.getId(), u.getNickname(), u.getPoint()))
                .toList();
    }

    public List<UserResponse> getRankingTop10WithSelf(User currentUser) {
        List<User> allUsers = userRepository.findAll();

        List<User> top10 = allUsers.stream()
                .sorted(Comparator.comparing(User::getPoint).reversed())
                .limit(10)
                .collect(Collectors.toList());

        if (!top10.contains(currentUser)) {
            top10.add(currentUser);
        }

        return top10.stream()
                .map(u -> new UserResponse(u.getId(), u.getNickname(), u.getPoint()))
                .toList();
    }

}
