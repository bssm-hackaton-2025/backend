package bssm.team15.hackaton.application.coupon;

import bssm.team15.hackaton.domain.coupon.Coupon;
import bssm.team15.hackaton.domain.experience.Experience;
import bssm.team15.hackaton.infrastructure.persistence.coupon.CouponRepository;
import bssm.team15.hackaton.presentation.reservation.dto.response.ExperienceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UseCouponUseCase {

    private final CouponRepository couponRepository;

    @Transactional
    public ExperienceResponse useCoupon(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("쿠폰을 찾을 수 없습니다."));

        Experience experience = coupon.getExperience();

        experience.discount(coupon.getDiscountAmount());

        coupon.use();

        couponRepository.save(coupon);

        return ExperienceResponse.from(experience);
    }
}
