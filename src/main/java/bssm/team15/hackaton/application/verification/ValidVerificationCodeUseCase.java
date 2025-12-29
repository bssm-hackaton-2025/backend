package bssm.team15.hackaton.application.verification;

import bssm.team15.hackaton.domain.verification.SignUpVerification;
import bssm.team15.hackaton.infrastructure.persistence.user.SignUpVerificationRepository;
import bssm.team15.hackaton.presentation.verification.dto.request.CodeRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ValidVerificationCodeUseCase {

    private final SignUpVerificationRepository signUpVerificationRepository;

    @Transactional
    public void validateVerificationCode(CodeRequest request){
        SignUpVerification signUpVerification = signUpVerificationRepository.findById(request.getEmail())
                .orElseThrow(() -> new NoSuchElementException("인증코드를 찾지 못함"));

        signUpVerification.verify(request.getCode());
    }

}
