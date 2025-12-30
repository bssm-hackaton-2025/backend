package bssm.team15.hackaton.infrastructure.gemini.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GeminiRequest {
    private List<Content> contents;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Content {
        private List<Part> parts;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Part {
        private String text;
    }

    public static GeminiRequest of(String prompt) {
        Part part = new Part(prompt);
        Content content = new Content(List.of(part));
        return new GeminiRequest(List.of(content));
    }
}
