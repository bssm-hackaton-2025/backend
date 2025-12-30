package bssm.team15.hackaton.infrastructure.gemini;

import bssm.team15.hackaton.infrastructure.gemini.dto.GeminiRequest;
import bssm.team15.hackaton.infrastructure.gemini.dto.GeminiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class GeminiClient {

    private final GeminiProperties properties;
    private final RestClient restClient = RestClient.create();

    public String generate(String prompt) {
        GeminiRequest request = GeminiRequest.of(prompt);

        GeminiResponse response = restClient.post()
                .uri(properties.getUrl() + "?key=" + properties.getKey())
                .header("Content-Type", "application/json")
                .body(request)
                .retrieve()
                .body(GeminiResponse.class);

        return response != null ? response.getText() : "";
    }
}
