package bssm.team15.hackaton.application.trash;

import bssm.team15.hackaton.domain.trash.Trash;
import bssm.team15.hackaton.domain.user.Role;
import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.infrastructure.persistence.trash.TrashRepository;
import bssm.team15.hackaton.presentation.trash.dto.response.TrashResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReadTrashesUseCase {
    private final TrashRepository trashRepository;

    @Value("${HOST}")
    private String host;

    public List<TrashResponse> readTrashes(User user) {
        if (user.getRole() == Role.ADMIN) {
            List<Trash> trashes = trashRepository.findAllByStatusNotNull();
            return trashes.stream()
                .map(trash -> new TrashResponse(
                        trash.getId(),
                        host + "/static/" + trash.getId() + "_1.jpeg",
                        trash.getLevel1Location(),
                        trash.getCreateAt(),
                        trash.getStatus() == null ? null : host + "/static/" + trash.getId() + "_2.jpeg",
                        trash.getStatus() == null ? null : trash.getLevel2Location(),
                        trash.getStatus() == null ? null : trash.getStatus().name()
                ))
                .collect(Collectors.toList());
        }
        else {
            List<Trash> trashes = trashRepository.findAllByUserId(user.getId());
            return trashes.stream()
                    .map(trash -> new TrashResponse(
                            trash.getId(),
                            host + "/static/" + trash.getId() + "_1.jpeg",
                            trash.getLevel1Location(),
                            trash.getCreateAt(),
                            trash.getStatus() == null ? null : host + "/static/" + trash.getId() + "_2.jpeg",
                            trash.getStatus() == null ? null : trash.getLevel2Location(),
                            trash.getStatus() == null ? null : trash.getStatus().name()
                    ))
                    .collect(Collectors.toList());
        }
    }
}
