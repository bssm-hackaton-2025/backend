package bssm.team15.hackaton.infrastructure.oceans;

import bssm.team15.hackaton.application.ocean.AnalyzeOceanTrashUseCase;

import java.util.List;

public record TrashAnalysisResponse(
        List<AnalyzeOceanTrashUseCase.HotspotInfo> topHotspots
) {}