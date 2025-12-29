package bssm.team15.hackaton.domain.verification;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.util.Random;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_signUpVerfication")
@Entity
public class SignUpVerification {

    @Id
    private String email;

    private String code;

    private boolean verified;

    @Builder
    private SignUpVerification(String email) {
        this.email = email;
        this.code = generateCode();
        this.verified = false;
    }

    public static SignUpVerification from(String email){
        return SignUpVerification.builder()
                .email(email)
                .build();
    }

    private String generateCode() {
        Random random = new Random();
        return String.valueOf(100000 + random.nextInt(900000));
    }

    public void verify(String requestCode) {
        validateCode(requestCode);
        this.verified = true;
    }

    private void validateCode(String requestCode) {
        if (!code.equals(requestCode)) {
            throw new IllegalArgumentException("틀린 인증코드입니다.");
        }
    }

    public void validateVerified() {
        if (!verified) {
            throw new IllegalArgumentException("인증되지않았습니다.");
        }
    }

}