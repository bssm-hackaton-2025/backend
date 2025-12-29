package bssm.team15.hackaton.application.auth;

import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.infrastructure.jwt.service.TokenService;
import bssm.team15.hackaton.infrastructure.persistence.user.UserRepository;
import bssm.team15.hackaton.presentation.auth.dto.request.LoginRequest;
import bssm.team15.hackaton.presentation.auth.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginUseCase {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못함"));

        validate(request.getPassword(), user.getPassword());

        return new TokenResponse(
                tokenService.generateAccessToken(user),
                tokenService.generateRefreshToken(user)
        );

    }

    private void validate(String rawPassword, String encodedPassword) {
        if (!bCryptPasswordEncoder.matches(rawPassword, encodedPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
