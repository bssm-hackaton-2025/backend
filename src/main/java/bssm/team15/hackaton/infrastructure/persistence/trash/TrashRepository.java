package bssm.team15.hackaton.infrastructure.persistence.trash;

import bssm.team15.hackaton.domain.trash.Trash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrashRepository extends JpaRepository <Trash, Long> {

    List<Trash> findAllByStatusNotNull();

    List<Trash> findAllByUserId(Long userId);
}
