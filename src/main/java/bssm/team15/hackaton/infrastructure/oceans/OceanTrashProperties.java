package bssm.team15.hackaton.infrastructure.oceans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "ocean-trash.api")
public class OceanTrashProperties {
    private String key;
    private String url;
}