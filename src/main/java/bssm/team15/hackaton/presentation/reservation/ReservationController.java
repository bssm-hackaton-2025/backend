package bssm.team15.hackaton.presentation.reservation;

import bssm.team15.hackaton.application.usecase.reservation.ReservationRequestUseCase;
import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.presentation.reservation.dto.request.ReservationRequestCreateRequest;
import bssm.team15.hackaton.presentation.reservation.dto.request.ReservationStatusRequest;
import bssm.team15.hackaton.presentation.reservation.dto.response.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationRequestUseCase reservationRequestUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponse createReservationRequest(
            @AuthenticationPrincipal User user,
            @RequestBody ReservationRequestCreateRequest request
    ) {
        return reservationRequestUseCase.createReservationRequest(requireUserId(user), request);
    }

    @GetMapping
    public List<ReservationResponse> getReservationRequests(
            @AuthenticationPrincipal User user
    ) {
        return reservationRequestUseCase.getReservationRequests(requireUserId(user));
    }

    @GetMapping("/{reservationId}")
    public ReservationResponse getReservationRequest(
            @AuthenticationPrincipal User user,
            @PathVariable Long reservationId
    ) {
        return reservationRequestUseCase.getReservationRequest(requireUserId(user), reservationId);
    }

    @PatchMapping("/{reservationId}/status")
    public ReservationResponse updateStatus(
            @AuthenticationPrincipal User user,
            @PathVariable Long reservationId,
            @RequestBody ReservationStatusRequest request
    ) {
        return reservationRequestUseCase.updateStatus(requireUserId(user), reservationId, request.getStatus());
    }

    @DeleteMapping("/{reservationId}")
    public ReservationResponse cancelReservation(
            @AuthenticationPrincipal User user,
            @PathVariable Long reservationId
    ) {
        return reservationRequestUseCase.cancelReservation(requireUserId(user), reservationId);
    }

    private Long requireUserId(User user) {
        if (user == null) {
            throw new IllegalArgumentException("인증이 필요합니다.");
        }
        return user.getId();
    }
}
