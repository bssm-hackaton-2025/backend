package bssm.team15.hackaton.presentation.coupon.dto.response;

import bssm.team15.hackaton.domain.coupon.Coupon;

import java.time.LocalDateTime;

public record CouponResponse(
        Long id,
        String store,
        String description,
        Long discountAmount,
        LocalDateTime expriationPeriod
) {
    public static CouponResponse from(Coupon coupon) {
        return new CouponResponse(
                coupon.getId(),
                coupon.getExperience().getBusinessName(),
                coupon.getDescription(),
                coupon.getDiscountAmount(),
                coupon.getExpiredAt()
        );
    }
}
