package bssm.team15.hackaton.infrastructure.persistence.trash;

import bssm.team15.hackaton.domain.trash.Trash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrashRepository extends JpaRepository <Trash, Long> {

}
