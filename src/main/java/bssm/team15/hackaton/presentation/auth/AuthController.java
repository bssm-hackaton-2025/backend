package bssm.team15.hackaton.presentation.auth;

import bssm.team15.hackaton.application.auth.LoginUseCase;
import bssm.team15.hackaton.application.auth.RefreshTokenUseCase;
import bssm.team15.hackaton.presentation.auth.dto.request.LoginRequest;
import bssm.team15.hackaton.presentation.auth.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;

    @PostMapping
    public TokenResponse login(
            @RequestBody LoginRequest request
    ){
        return loginUseCase.login(request);
    }

    @PatchMapping("/refresh")
    public TokenResponse refreshToken(
            @RequestHeader(name = "refresh-token") String refreshToken
    ){
        return refreshTokenUseCase.refreshToken(refreshToken);
    }
}
