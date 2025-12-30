package bssm.team15.hackaton.presentation.notification;

import bssm.team15.hackaton.application.notification.ReservationNotificationService;
import bssm.team15.hackaton.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final ReservationNotificationService reservationNotificationService;

    @GetMapping(value = "/{ownerId}/reservations", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeReservations(
            @AuthenticationPrincipal User user,
            @PathVariable Long ownerId
    ) {
        if (user == null || !user.getId().equals(ownerId)) {
            throw new AccessDeniedException("해당 알림 채널에 접근할 수 없습니다.");
        }
        return reservationNotificationService.subscribe(ownerId);
    }
}
