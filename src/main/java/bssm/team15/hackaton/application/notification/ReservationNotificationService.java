package bssm.team15.hackaton.application.notification;

import bssm.team15.hackaton.domain.reservation.ReservationRequest;
import bssm.team15.hackaton.domain.reservation.ReservationStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ReservationNotificationService {

    private final Map<Long, List<SseEmitter>> emitters = new ConcurrentHashMap<>();

    public SseEmitter subscribe(Long ownerId) {
        SseEmitter emitter = new SseEmitter(0L);
        emitters.computeIfAbsent(ownerId, key -> new CopyOnWriteArrayList<>()).add(emitter);

        emitter.onCompletion(() -> removeEmitter(ownerId, emitter));
        emitter.onTimeout(() -> removeEmitter(ownerId, emitter));
        emitter.onError((ex) -> removeEmitter(ownerId, emitter));

        return emitter;
    }

    public void notifyOwner(Long ownerId, ReservationRequest request, ReservationStatus status, String type) {
        ReservationNotification payload = new ReservationNotification(
                request.getId(),
                ownerId,
                status,
                type,
                LocalDateTime.now()
        );

        List<SseEmitter> ownerEmitters = emitters.getOrDefault(ownerId, List.of());
        for (SseEmitter emitter : ownerEmitters) {
            try {
                emitter.send(SseEmitter.event().name("reservation").data(payload));
            } catch (IOException e) {
                removeEmitter(ownerId, emitter);
            }
        }
    }

    private void removeEmitter(Long ownerId, SseEmitter emitter) {
        List<SseEmitter> ownerEmitters = emitters.get(ownerId);
        if (ownerEmitters != null) {
            ownerEmitters.remove(emitter);
            if (ownerEmitters.isEmpty()) {
                emitters.remove(ownerId);
            }
        }
    }
}
