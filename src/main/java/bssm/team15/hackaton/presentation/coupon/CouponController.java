package bssm.team15.hackaton.presentation.coupon;

import bssm.team15.hackaton.application.coupon.CreateCouponUseCase;
import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.presentation.coupon.dto.request.CreateCouponRequest;
import bssm.team15.hackaton.presentation.coupon.dto.response.CouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/experience/{experienceId}/coupons")
public class CouponController {

    private final CreateCouponUseCase createCouponUseCase;

    @PostMapping
    public CouponResponse createCoupon(
            @AuthenticationPrincipal User user,
            @PathVariable Long experienceId,
            @RequestBody CreateCouponRequest request
    ){
        return createCouponUseCase.create(user, request, experienceId);
    }
}
