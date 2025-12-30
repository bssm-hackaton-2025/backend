package bssm.team15.hackaton.domain.coupon;

import bssm.team15.hackaton.domain.experience.Experience;
import bssm.team15.hackaton.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_coupon")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(nullable = false)
    private Long discountAmount;

    @Column(nullable = false)
    private Boolean isUsed = false;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "experience_id")
    private Experience experience;

    @Builder
    public Coupon(String description, Long discountAmount, Integer day, Experience experience) {
        this.description = description;
        this.discountAmount = discountAmount;
        this.expiredAt = LocalDateTime.now().plusDays(day);
        this.experience = experience;
        this.isUsed = false;
    }

    public void use() {
        if (isUsed) {
            throw new IllegalStateException("이미 사용된 쿠폰입니다.");
        }
        this.isUsed = true;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiredAt);
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
