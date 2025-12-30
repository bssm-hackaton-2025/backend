package bssm.team15.hackaton.application.trash;

import bssm.team15.hackaton.domain.trash.Trash;
import bssm.team15.hackaton.domain.user.User;
import bssm.team15.hackaton.infrastructure.persistence.trash.TrashRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class SetStatusTrashUseCase {
    private final TrashRepository trashRepository;

    @Transactional
    public void confirm(User user, Long id) {
        Trash trash = trashRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                ""
        ));
        if (!trash.statusIsSettable()) throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                ""
        );

        trash.confirm();
    }

    @Transactional
    public void reject(User user, Long id, String Reason) {
        Trash trash = trashRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                ""
        ));
        if (!trash.statusIsSettable()) throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                ""
        );

        trash.reject(Reason);
    }

}
