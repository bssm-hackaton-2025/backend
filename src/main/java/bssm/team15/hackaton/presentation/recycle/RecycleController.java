package bssm.team15.hackaton.presentation.recycle;

import bssm.team15.hackaton.application.recycle.GetRecycleGuideUseCase;
import bssm.team15.hackaton.infrastructure.gemini.GeminiProperties;
import bssm.team15.hackaton.presentation.recycle.dto.request.RecycleGuideRequest;
import bssm.team15.hackaton.presentation.recycle.dto.response.RecycleGuideResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recycle")
@RequiredArgsConstructor
public class RecycleController {

    private final GetRecycleGuideUseCase getRecycleGuideUseCase;
    private final GeminiProperties properties;

    @PostMapping("/guide")
    public RecycleGuideResponse getGuide(@RequestBody RecycleGuideRequest request) {
        return getRecycleGuideUseCase.getGuide(request.getTrashName(), request.getLocation());
    }

    @GetMapping("/gemini-config")
    public String testConfig() {
        return String.format("""
            Key exists: %s
            Key length: %d
            Key preview: %s
            URL: %s
            """,
                properties.getKey() != null,
                properties.getKey() != null ? properties.getKey().length() : 0,
                properties.getKey() != null ? properties.getKey().substring(0, 10) + "..." : "null",
                properties.getUrl()
        );
    }

}
