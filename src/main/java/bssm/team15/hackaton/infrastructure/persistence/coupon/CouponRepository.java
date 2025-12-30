package bssm.team15.hackaton.infrastructure.persistence.coupon;

import bssm.team15.hackaton.domain.coupon.Coupon;
import bssm.team15.hackaton.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findByOwnerAndIsUsedFalse(User owner);
    List<Coupon> findByOwner(User owner);
}
