package bssm.team15.hackaton.application.user;

import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.domain.verification.SignUpVerification;
import bssm.team15.hackaton.infrastructure.persistence.user.SignUpVerificationRepository;
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
    private final SignUpVerificationRepository signUpVerificationRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void signUp(SignUpRequest request) {
        validate(request.getEmail());

        userRepository.save(
                User.from(
                        request.getEmail(),
                        request.getNickname(),
                        bCryptPasswordEncoder.encode(request.getPassword())
                )
        );

    }

    private void validate(String email){
        SignUpVerification verification = signUpVerificationRepository.findById(email)
                .orElseThrow(() -> new IllegalArgumentException("인증코드를 찾을 수 없습니다"));

        if(!verification.isVerified()){
            throw new IllegalArgumentException("인증되지 않은 코드입니다.");
        }else{
            signUpVerificationRepository.delete(verification);
        }
    }


}
