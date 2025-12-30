package bssm.team15.hackaton.domain.experience;

import bssm.team15.hackaton.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String location;
    private String description;
    private Long price;
    private Integer maxParticipants;
    private String businessRegistrationNumber;
    private String businessName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    public Experience(String title, String location, String description, Long price, Integer maxParticipants, String businessRegistrationNumber, String businessName, User user) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.price = price;
        this.maxParticipants = maxParticipants;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.businessName = businessName;
        this.owner = user;
    }

    public void discount(Long discountAmount) {
        this.price -= discountAmount;
    }
}