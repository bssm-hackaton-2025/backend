package bssm.team15.hackaton.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tbl_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true,  nullable = false, length = 200)
    private String nickname;

    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean isOwner = false;

    @Column(nullable = false)
    private Long point = 0L;

    @Builder
    private User(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.role = Role.USER;
    }

    public static User from(
            String email,
            String nickname,
            String password
    ) {
        return User.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
    }

    public void isOwner() {
        this.isOwner = !this.isOwner;
    }

    public void addPoint(Long point){
        if(point < 0){
            throw new IllegalArgumentException("음수는 추가할 수 없습니다.");
        }
        this.point += point;
    }

}
