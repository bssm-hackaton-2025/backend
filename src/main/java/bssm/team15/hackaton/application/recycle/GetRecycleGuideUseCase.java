package bssm.team15.hackaton.application.recycle;

import bssm.team15.hackaton.infrastructure.gemini.GeminiClient;
import bssm.team15.hackaton.presentation.recycle.dto.response.RecycleGuideResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class GetRecycleGuideUseCase {

    private final GeminiClient geminiClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RecycleGuideResponse getGuide(String trashName, String location) {
        String prompt = createPrompt(trashName, location);
        String aiResponse = geminiClient.generate(prompt);

        return parseResponse(aiResponse, trashName);
    }

    private String createPrompt(String trashName, String location) {
        return String.format("""
        %s 지역, '%s' 분리배출 정보:
        
        쓰레기 종류:
        분류: (일반/재활용/음식물/대형 중 택1)
        분리배출 방법: (2-3문장)
        버리는 곳: (%s 기준, 1-2문장)
        추가 팁: (1-2문장)
        
        위 형식으로만 답변.
        """, location, trashName, location);
    }

    private RecycleGuideResponse parseResponse(String aiResponse, String trashName) {
        try {
            String[] lines = aiResponse.split("\n");
            String trashType = trashName;
            String category = "";
            String howToSeparate = "";
            String whereToDispose = "";
            String additionalTips = "";

            StringBuilder currentSection = new StringBuilder();
            String currentField = "";

            for (String line : lines) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("쓰레기 종류:")) {
                    trashType = line.replace("쓰레기 종류:", "").trim();
                    currentField = "";
                } else if (line.startsWith("분류:")) {
                    category = line.replace("분류:", "").trim();
                    currentField = "";
                } else if (line.startsWith("분리배출 방법:")) {
                    if (!currentSection.isEmpty()) {
                        assignToField(currentField, currentSection.toString().trim());
                    }
                    currentField = "howToSeparate";
                    currentSection = new StringBuilder();
                    String content = line.replace("분리배출 방법:", "").trim();
                    if (!content.isEmpty()) currentSection.append(content);
                } else if (line.startsWith("버리는 곳:")) {
                    if (!currentSection.isEmpty() && currentField.equals("howToSeparate")) {
                        howToSeparate = currentSection.toString().trim();
                    }
                    currentField = "whereToDispose";
                    currentSection = new StringBuilder();
                    String content = line.replace("버리는 곳:", "").trim();
                    if (!content.isEmpty()) currentSection.append(content);
                } else if (line.startsWith("추가 팁:")) {
                    if (!currentSection.isEmpty() && currentField.equals("whereToDispose")) {
                        whereToDispose = currentSection.toString().trim();
                    }
                    currentField = "additionalTips";
                    currentSection = new StringBuilder();
                    String content = line.replace("추가 팁:", "").trim();
                    if (!content.isEmpty()) currentSection.append(content);
                } else if (!currentField.isEmpty()) {
                    if (currentSection.length() > 0) currentSection.append(" ");
                    currentSection.append(line);
                }
            }

            // 마지막 섹션 처리
            if (!currentSection.isEmpty()) {
                if (currentField.equals("howToSeparate")) {
                    howToSeparate = currentSection.toString().trim();
                } else if (currentField.equals("whereToDispose")) {
                    whereToDispose = currentSection.toString().trim();
                } else if (currentField.equals("additionalTips")) {
                    additionalTips = currentSection.toString().trim();
                }
            }

            return new RecycleGuideResponse(
                    trashType,
                    category,
                    howToSeparate,
                    whereToDispose,
                    additionalTips
            );

        } catch (Exception e) {
            // 파싱 실패 시 전체 응답을 방법에 넣어서 반환
            return new RecycleGuideResponse(
                    trashName,
                    "알 수 없음",
                    aiResponse,
                    "상세 정보는 분리배출 방법을 참고하세요.",
                    ""
            );
        }
    }

    private void assignToField(String field, String value) {
        // 이 메서드는 사용하지 않지만 구조상 남겨둠
    }
}
