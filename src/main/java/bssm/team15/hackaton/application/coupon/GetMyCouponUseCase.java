package bssm.team15.hackaton.application.coupon;

import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.infrastructure.persistence.coupon.CouponRepository;
import bssm.team15.hackaton.presentation.coupon.dto.response.CouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetMyCouponUseCase {

    private final CouponRepository couponRepository;

    public List<CouponResponse> getMyCoupons(User user){
        return couponRepository.findByOwner(user)
                .stream()
                .map(CouponResponse::from)
                .toList();
    }

}
