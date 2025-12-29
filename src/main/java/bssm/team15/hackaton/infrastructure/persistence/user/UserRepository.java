package bssm.team15.hackaton.infrastructure.persistence.user;

import bssm.team15.hackaton.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
