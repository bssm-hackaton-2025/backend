package bssm.team15.hackaton.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "ReservationUser")
@Table(name = "tbl_reservation_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String role; // USER or OWNER
}
