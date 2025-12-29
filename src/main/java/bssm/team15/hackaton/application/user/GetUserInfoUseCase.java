package bssm.team15.hackaton.application.user;

import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.presentation.user.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetUserInfoUseCase {

    public UserResponse getUserInfo(User user) {
        return new UserResponse(
                user.getId(),
                user.getNickname()
        );
    }

}
