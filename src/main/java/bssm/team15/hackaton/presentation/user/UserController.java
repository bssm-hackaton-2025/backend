package bssm.team15.hackaton.presentation.user;

import bssm.team15.hackaton.application.coupon.GetMyCouponUseCase;
import bssm.team15.hackaton.application.coupon.UseCouponUseCase;
import bssm.team15.hackaton.application.user.GetRankingUseCase;
import bssm.team15.hackaton.application.user.GetUserDetailInfoUseCase;
import bssm.team15.hackaton.application.user.GetUserInfoUseCase;
import bssm.team15.hackaton.application.user.SignUpUseCase;
import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.presentation.coupon.dto.response.CouponResponse;
import bssm.team15.hackaton.presentation.reservation.dto.response.ExperienceResponse;
import bssm.team15.hackaton.presentation.user.dto.request.SignUpRequest;
import bssm.team15.hackaton.presentation.user.dto.response.UserDetailResponse;
import bssm.team15.hackaton.presentation.user.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final SignUpUseCase signUpUseCase;
    private final GetUserInfoUseCase getUserInfoUseCase;
    private final GetUserDetailInfoUseCase getUserDetailInfoUseCase;
    private final GetRankingUseCase getRankingUseCase;
    private final UseCouponUseCase  useCouponUseCase;
    private final GetMyCouponUseCase getMyCouponUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(
            @RequestBody SignUpRequest request
    ){
        signUpUseCase.signUp(request);
    }

    @GetMapping
    public UserResponse getUserInfo(
            @AuthenticationPrincipal User user
    ) {
        return getUserInfoUseCase.getUserInfo(user);
    }

    @GetMapping("/info")
    public UserDetailResponse getUserDetailInfo(
            @AuthenticationPrincipal User user
    ){
        return getUserDetailInfoUseCase.getUserDetailInfo(user);
    }

    @GetMapping("/rank")
    public List<UserResponse> getRanking(){
        return getRankingUseCase.getRankingTop5();
    }

    @GetMapping("/rank/10")
    public List<UserResponse> getRanking10(
            @AuthenticationPrincipal User user
    ){
        return getRankingUseCase.getRankingTop10WithSelf(user);
    }

    @PatchMapping("/{couponId}/use")
    public ExperienceResponse useCoupon(
            @PathVariable Long couponId,
            @AuthenticationPrincipal User user
    ){
        return useCouponUseCase.useCoupon(couponId);
    }

    @GetMapping("/coupon")
    public List<CouponResponse> getMyCoupon(
            @AuthenticationPrincipal User user
    ){
        return  getMyCouponUseCase.getMyCoupons(user);
    }

}
