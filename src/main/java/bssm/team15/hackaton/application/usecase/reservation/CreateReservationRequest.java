package bssm.team15.hackaton.application.usecase.reservation; // 패키지 선언 추가

import java.time.LocalDateTime; // LocalDateTime을 쓰기 위해 필수

public record CreateReservationRequest(
        Long experienceId,
        LocalDateTime reservedDate,
        int participantCount
) {}