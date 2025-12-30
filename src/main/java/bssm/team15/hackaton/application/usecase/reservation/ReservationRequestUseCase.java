package bssm.team15.hackaton.application.usecase.reservation;

import bssm.team15.hackaton.application.notification.ReservationNotificationService;
import bssm.team15.hackaton.domain.reservation.ReservationRequest;
import bssm.team15.hackaton.domain.reservation.ReservationStatus;
import bssm.team15.hackaton.infrastructure.persistence.reservation.ReservationRequestRepository;
import bssm.team15.hackaton.presentation.reservation.dto.request.ReservationRequestCreateRequest;
import bssm.team15.hackaton.presentation.reservation.dto.response.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationRequestUseCase {

    private final ReservationRequestRepository reservationRequestRepository;
    private final ReservationNotificationService notificationService;

    @Transactional
    public ReservationResponse createReservationRequest(Long ownerId, ReservationRequestCreateRequest request) {
        ReservationRequest reservationRequest = ReservationRequest.builder()
                .ownerId(ownerId)
                .businessRegistrationNumber(request.getBusinessRegistrationNumber())
                .businessName(request.getBusinessName())
                .ownerName(request.getOwnerName())
                .location(request.getLocation())
                .experienceName(request.getExperienceName())
                .description(request.getDescription())
                .price(request.getPrice())
                .maxParticipants(request.getMaxParticipants())
                .build();

        ReservationRequest saved = reservationRequestRepository.save(reservationRequest);
        notificationService.notifyOwner(ownerId, saved, saved.getStatus(), "CREATED");

        return ReservationResponse.from(saved);
    }

    public List<ReservationResponse> getReservationRequests(Long ownerId) {
        return reservationRequestRepository.findAllByOwnerId(ownerId)
                .stream()
                .map(ReservationResponse::from)
                .toList();
    }

    public ReservationResponse getReservationRequest(Long ownerId, Long reservationId) {
        ReservationRequest reservation = getOwnedReservation(ownerId, reservationId);
        return ReservationResponse.from(reservation);
    }

    @Transactional
    public ReservationResponse updateStatus(Long ownerId, Long reservationId, ReservationStatus status) {
        ReservationRequest reservation = getOwnedReservation(ownerId, reservationId);
        reservation.updateStatus(status);
        notificationService.notifyOwner(ownerId, reservation, status, "STATUS_UPDATED");
        return ReservationResponse.from(reservation);
    }

    @Transactional
    public ReservationResponse cancelReservation(Long ownerId, Long reservationId) {
        ReservationRequest reservation = getOwnedReservation(ownerId, reservationId);
        reservation.updateStatus(ReservationStatus.CANCELLED);
        notificationService.notifyOwner(ownerId, reservation, ReservationStatus.CANCELLED, "CANCELLED");
        return ReservationResponse.from(reservation);
    }

    private ReservationRequest getOwnedReservation(Long ownerId, Long reservationId) {
        ReservationRequest reservation = reservationRequestRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("예약 요청을 찾을 수 없습니다."));
        if (!reservation.getOwnerId().equals(ownerId)) {
            throw new AccessDeniedException("해당 예약 요청에 접근할 수 없습니다.");
        }
        return reservation;
    }
}
