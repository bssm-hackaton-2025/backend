package bssm.team15.hackaton.application.user;

import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.infrastructure.persistence.user.UserRepository;
import bssm.team15.hackaton.presentation.user.dto.request.SignUpRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignUpUseCase {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void signUp(SignUpRequest request) {

        userRepository.save(
                User.from(
                        request.getEmail(),
                        request.getNickname(),
                        bCryptPasswordEncoder.encode(request.getPassword())
                )
        );

    }
}
