package bssm.team15.hackaton.application.usecase.reservation;

import bssm.team15.hackaton.domain.entity.Experience;
import bssm.team15.hackaton.domain.entity.User;
import bssm.team15.hackaton.domain.reservation.Reservation;
import bssm.team15.hackaton.domain.reservation.ReservationStatus;
import bssm.team15.hackaton.infrastructure.persistence.reservation.ExperienceRepository;
import bssm.team15.hackaton.infrastructure.persistence.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 기본적으로 읽기 전용으로 설정
public class ReservationUseCase {

    private final ReservationRepository reservationRepository;
    private final ExperienceRepository experienceRepository;

    /**
     * 신규 예약 생성
     */
    @Transactional // 데이터 변경이 일어나므로 Transactional 추가
    public Reservation createReservation(CreateReservationRequest request, Long userId) {

        // 1. 해당 체험지가 존재하는지 확인
        Experience experience = experienceRepository.findById(request.experienceId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 체험존입니다. ID: " + request.experienceId()));

        // 2. 현재 예약된 인원 확인 (대기 또는 승인 상태인 인원만 합산)
        long currentCount = reservationRepository.countByExperienceIdAndStatusIn(
                request.experienceId(),
                List.of(ReservationStatus.PENDING, ReservationStatus.APPROVED)
        );

        // 3. 정원 초과 검증
        if (currentCount + request.participantCount() > experience.getMaxParticipants()) {
            throw new IllegalStateException("정원이 초과되었습니다. 남은 자리: " + (experience.getMaxParticipants() - currentCount));
        }

        // 4. 예약 엔티티 생성
        // 주의: User.builder().id(userId).build()는 영속성 컨텍스트에 없는 객체이므로
        // 실제 운영 환경에서는 UserRepository.getReferenceById(userId)를 권장합니다.
        User user = User.builder()
                .id(userId)
                .build();

        Reservation reservation = Reservation.builder()
                .user(user)
                .experience(experience)
                .visitAt(request.reservedDate()) // 요청의 날짜를 엔티티의 방문일로 매핑
                .participantCount(request.participantCount())
                .build();

        // 5. DB 저장 및 반환
        return reservationRepository.save(reservation);
    }

    /**
     * 특정 예약의 상태 변경 (승인/거절/취소 등)
     */
    @Transactional
    public void changeStatus(Long reservationId, ReservationStatus newStatus) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("예약을 찾을 수 없습니다. ID: " + reservationId));

        reservation.updateStatus(newStatus);
    }

    /**
     * 나의 예약 목록 조회
     */
    public List<Reservation> getMyReservations(Long userId) {
        return reservationRepository.findAllByUserId(userId);
    }
}