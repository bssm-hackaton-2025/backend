package bssm.team15.hackaton.application.game;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class GameSseManager {

    private final ConcurrentHashMap<Long, List<SseEmitter>> emitters = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public SseEmitter subscribe(Long gameId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        emitters.compute(gameId, (key, list) -> {
            if (list == null) list = new CopyOnWriteArrayList<>();
            list.add(emitter);
            return list;
        });

        emitter.onCompletion(() -> removeEmitter(gameId, emitter));
        emitter.onTimeout(() -> removeEmitter(gameId, emitter));

        return emitter;
    }

    public void send(Long gameId, Object data, String eventName) {
        List<SseEmitter> list = emitters.get(gameId);
        if (list == null) return;

        String json;
        try {
            json = objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            return;
        }

        for (SseEmitter emitter : list) {
            try {
                emitter.send(SseEmitter.event()
                        .name(eventName)
                        .data(json));
            } catch (IOException e) {
                removeEmitter(gameId, emitter);
            }
        }
    }

    private void removeEmitter(Long gameId, SseEmitter emitter) {
        List<SseEmitter> list = emitters.get(gameId);
        if (list != null) {
            list.remove(emitter);
            if (list.isEmpty()) {
                emitters.remove(gameId);
            }
        }
    }
}
