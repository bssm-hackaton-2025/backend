package bssm.team15.hackaton.application.ocean;

import bssm.team15.hackaton.infrastructure.gemini.GeminiClient;
import bssm.team15.hackaton.infrastructure.oceans.OceanTrashClient;
import bssm.team15.hackaton.infrastructure.oceans.TrashAnalysisResponse;
import bssm.team15.hackaton.infrastructure.oceans.dto.OceanTrashResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalyzeOceanTrashUseCase {

    private final OceanTrashClient oceanTrashClient;
    private final GeminiClient geminiClient;

    public TrashAnalysisResponse analyze() {
        // 전국 조회 시 zoneName null 처리
        OceanTrashResponse response = oceanTrashClient.getAllTrashData(null, "2024");

        if (response.getBody() == null ||
                response.getBody().getItems() == null ||
                response.getBody().getItems().getItemList().isEmpty()) {
            return new TrashAnalysisResponse(
                    Collections.emptyList()
            );
        }

        List<OceanTrashResponse.Item> items = response.getBody().getItems().getItemList();

        Map<String, Statistics> stats = items.stream()
                .collect(Collectors.groupingBy(
                        item -> item.getMonitoringZoneName(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> new Statistics(
                                        list.stream().mapToInt(i -> i.getMonitoringPeoplenum() != null ? i.getMonitoringPeoplenum() : 0).sum(),
                                        list.stream().mapToDouble(i -> i.getMonitoringKg() != null ? i.getMonitoringKg() : 0).sum()
                                )
                        )
                ));

        List<HotspotInfo> hotspots = stats.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue().totalWeight, e1.getValue().totalWeight))
                .limit(10)
                .map(e -> new HotspotInfo(
                        e.getKey()
                ))
                .toList();

        String prompt = createPrompt(hotspots);
        String aiAnalysis = geminiClient.generate(prompt);

        return new TrashAnalysisResponse(hotspots);
    }

    private String createPrompt(List<HotspotInfo> hotspots) {
        StringBuilder data = new StringBuilder("2024년 해안쓰레기 TOP 10:\n");
        for (int i = 0; i < hotspots.size(); i++) {
            HotspotInfo h = hotspots.get(i);
            data.append(String.format("%s", h.locationName));
        }

        return String.format("""
            %s
            
            간단 분석 (3문장):
            1. 가장 문제가 심각한 지역
            2. 주요 원인
            3. 해결 방안
            """, data);
    }

    private record Statistics(int totalCount, double totalWeight) {}
    public record HotspotInfo(String locationName) {}
}
