package bssm.team15.hackaton.infrastructure.oceans;

import bssm.team15.hackaton.infrastructure.oceans.dto.OceanTrashResponse;
import io.netty.channel.ChannelOption;
import lombok.RequiredArgsConstructor;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Component
@RequiredArgsConstructor
public class OceanTrashClient {

    private final OceanTrashProperties properties;
    private final WebClient webClient = WebClient.builder()
            .clientConnector(
                    new ReactorClientHttpConnector(
                            HttpClient.from(
                                    TcpClient.create()
                                            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                            ).responseTimeout(Duration.ofSeconds(30))
                    )
            )
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024)) // 16MB 버퍼
            .build();

    public OceanTrashResponse getTrashData(String startYear, String endYear, Integer monitoringChasu, int pageNo, int numOfRows) {

        String encodedKey = URLEncoder.encode(properties.getKey(), StandardCharsets.UTF_8);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(properties.getUrl())
                .queryParam("serviceKey", encodedKey)
                .queryParam("pageNo", pageNo)
                .queryParam("numOfRows", numOfRows)
                .queryParam("monitoringSYr", startYear)
                .queryParam("monitoringEYr", endYear);

        if (monitoringChasu != null) {
            builder.queryParam("monitoringChasu", monitoringChasu);
        }

        String url = builder.toUriString();

        System.out.println("Request URL: " + url);

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(OceanTrashResponse.class)
                .block();
    }

    public OceanTrashResponse getAllTrashData(String startYear, String endYear) {
        return getTrashData(startYear, endYear, null, 1, 50);
    }
}
