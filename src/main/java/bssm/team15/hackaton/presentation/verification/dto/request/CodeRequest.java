package bssm.team15.hackaton.presentation.verification.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class CodeRequest {

    private String email;

    private String code;

}
