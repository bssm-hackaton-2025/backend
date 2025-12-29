package bssm.team15.hackaton.application.verification;

import bssm.team15.hackaton.domain.verification.SignUpVerification;
import bssm.team15.hackaton.infrastructure.mail.MailUtil;
import bssm.team15.hackaton.infrastructure.persistence.user.SignUpVerificationRepository;
import bssm.team15.hackaton.presentation.verification.dto.request.VerificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SendVerificationUseCase {

    private final SignUpVerificationRepository signUpVerificationRepository;
    private final MailUtil mailUtil;

    public void sendVerificationCode(VerificationRequest request){

        SignUpVerification verification = signUpVerificationRepository
                .save(
                        SignUpVerification.from(request.getEmail())
                );

        mailUtil.sendMimeMessage(verification.getEmail(), verification.getCode());
    }
}
