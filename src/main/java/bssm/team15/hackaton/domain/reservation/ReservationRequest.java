package bssm.team15.hackaton.domain.reservation;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_reservation_request")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ownerId;

    private String businessRegistrationNumber;

    private String businessName;

    private String ownerName;

    private String location;

    private String experienceName;

    @Column(length = 2000)
    private String description;

    private Long price;

    private Integer maxParticipants;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    private ReservationRequest(
            Long ownerId,
            String businessRegistrationNumber,
            String businessName,
            String ownerName,
            String location,
            String experienceName,
            String description,
            Long price,
            Integer maxParticipants
    ) {
        this.ownerId = ownerId;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.businessName = businessName;
        this.ownerName = ownerName;
        this.location = location;
        this.experienceName = experienceName;
        this.description = description;
        this.price = price;
        this.maxParticipants = maxParticipants;
        this.status = ReservationStatus.PENDING;
    }

    public void updateStatus(ReservationStatus status) {
        this.status = status;
    }

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
