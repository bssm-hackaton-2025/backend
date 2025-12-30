package bssm.team15.hackaton.domain.reservation;

import bssm.team15.hackaton.domain.entity.Experience;
import bssm.team15.hackaton.domain.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Experience experience;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private LocalDateTime visitAt;
    private Integer participantCount;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Builder
    public Reservation(Experience experience, User user, LocalDateTime visitAt, Integer participantCount) {
        this.experience = experience;
        this.user = user;
        this.visitAt = visitAt;
        this.participantCount = participantCount;
        this.status = ReservationStatus.PENDING;
    }

    public void updateStatus(ReservationStatus newStatus) {
        this.status = newStatus;
    }
}