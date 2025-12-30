package bssm.team15.hackaton.application.coupon;

import bssm.team15.hackaton.domain.coupon.Coupon;
import bssm.team15.hackaton.domain.experience.Experience;
import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.infrastructure.persistence.coupon.CouponRepository;
import bssm.team15.hackaton.infrastructure.persistence.reservation.ExperienceRepository;
import bssm.team15.hackaton.presentation.coupon.dto.request.CreateCouponRequest;
import bssm.team15.hackaton.presentation.coupon.dto.response.CouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateCouponUseCase {

    private final CouponRepository couponRepository;
    private final ExperienceRepository experienceRepository;

    public CouponResponse create(User user, CreateCouponRequest request, Long id){
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("체험을 찾을 수 없습니다."));

        Coupon coupon = couponRepository.save(
                Coupon.builder()
                        .description(request.getDescription())
                        .discountAmount(request.getDiscountAmount())
                        .day(request.getDay())
                        .experience(experience)
                        .build()
        );

        return CouponResponse.from(coupon);
    }

}
