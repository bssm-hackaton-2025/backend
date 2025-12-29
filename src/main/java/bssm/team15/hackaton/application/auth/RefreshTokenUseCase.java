package bssm.team15.hackaton.application.auth;

import bssm.team15.hackaton.infrastructure.jwt.service.TokenService;
import bssm.team15.hackaton.presentation.auth.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenUseCase {

    private final TokenService tokenService;

    public TokenResponse refreshToken(String refreshToken) {
        return tokenService.refreshAccessToken(refreshToken);
    }
}
