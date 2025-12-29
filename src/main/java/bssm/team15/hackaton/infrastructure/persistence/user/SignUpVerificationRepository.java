package bssm.team15.hackaton.infrastructure.persistence.user;

import bssm.team15.hackaton.domain.verification.SignUpVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignUpVerificationRepository extends JpaRepository<SignUpVerification, String> {
}
