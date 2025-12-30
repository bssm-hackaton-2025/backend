package bssm.team15.hackaton.presentation.coupon.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCouponRequest {

    private String name;

    private String description;

    private Integer day;

    private Long discountAmount;

}
