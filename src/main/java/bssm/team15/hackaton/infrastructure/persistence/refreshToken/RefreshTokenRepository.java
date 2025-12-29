package bssm.team15.hackaton.infrastructure.persistence.refreshToken;

import bssm.team15.hackaton.infrastructure.jwt.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findById(Long id);
}
