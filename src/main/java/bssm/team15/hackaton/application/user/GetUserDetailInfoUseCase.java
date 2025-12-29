package bssm.team15.hackaton.application.user;

import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.presentation.user.dto.response.UserDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetUserDetailInfoUseCase {

    public UserDetailResponse getUserDetailInfo(User user) {
        return new UserDetailResponse(
                user.getId(),
                user.getEmail(),
                user.getNickname(),
                user.getIsOwner()
        );
    }

}
