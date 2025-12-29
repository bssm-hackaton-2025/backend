package bssm.team15.hackaton.presentation.user.dto.response;

public record UserDetailResponse(
        Long id,
        String email,
        String nickname,
        Boolean isOwner
) {
}
