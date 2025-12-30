package bssm.team15.hackaton.presentation.recycle.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecycleGuideRequest {

    private String trashName;

    private String location;
}
