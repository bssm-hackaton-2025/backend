package bssm.team15.hackaton.domain.reservation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReservationStatus {
    PENDING("대기"), APPROVED("승인"), REJECTED("거절"), CANCELLED("취소"), COMPLETED("완료");
    private final String description;
}
