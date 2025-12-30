package bssm.team15.hackaton.domain.trash;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_trash")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String level1Location;

    @Column
    private String level2Location;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;

    @Column
    private String rejectReason;

    @Column(nullable = false)
    private LocalDateTime createAt;

    public static Trash create(
            Long userId,
            String location
    ) {
        Trash trash = new Trash();
        trash.userId = userId;
        trash.level1Location = location;
        trash.createAt = LocalDateTime.now();
        return trash;
    }

    public void certify (
            String location
    ) {
        this.level2Location = location;
        this.status = ApprovalStatus.pending;
    }

    public boolean statusIsSettable() {
        return this.status != null;
    }

    public void confirm() {
        this.status = ApprovalStatus.confirmed;
    }

    public void reject(
            String reason
    ) {
        this.status = ApprovalStatus.rejected;
        this.rejectReason = reason;
    }

}
