package bssm.team15.hackaton.presentation.recycle.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecycleGuideResponse {
    private String trashType;
    private String category;
    private String howToSeparate;
    private String whereToDispose;
    private String additionalTips;
}
