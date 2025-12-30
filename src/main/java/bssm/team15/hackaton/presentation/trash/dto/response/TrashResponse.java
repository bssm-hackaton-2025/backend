package bssm.team15.hackaton.presentation.trash.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TrashResponse {
    Long trashId;
    String imageURL;
    String location;
    LocalDateTime createdAt;
    String secondImageURL;
    String secondLocation;
    String certified;
}
