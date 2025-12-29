package bssm.team15.hackaton.presentation.verification;

import bssm.team15.hackaton.application.verification.SendVerificationUseCase;
import bssm.team15.hackaton.application.verification.ValidVerificationCodeUseCase;
import bssm.team15.hackaton.presentation.verification.dto.request.CodeRequest;
import bssm.team15.hackaton.presentation.verification.dto.request.VerificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/verification")
public class VerificationController {

    private final SendVerificationUseCase sendVerificationUseCase;
    private final ValidVerificationCodeUseCase validVerificationCodeUseCase;

    @PostMapping
    public void sendVerificationCode(
            @RequestBody VerificationRequest request
    ){
        sendVerificationUseCase.sendVerificationCode(request);
    }

    @PatchMapping
    public void validVerificationCode(
            @RequestBody CodeRequest request
    ){
        validVerificationCodeUseCase.validateVerificationCode(request);
    }

}
