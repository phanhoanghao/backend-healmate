package bluedragonvn.com.healmate.repository.dao;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER")
@Builder
public class User extends DateAudit{
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.DETACH)
    @JoinTable(name = "USER_ROLES",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    Set<Role> roles;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "USER_ID", updatable = false, nullable = false)
    private String userId;
    @NotNull
    @NotBlank
    @Column(name = "PHONE", updatable = false, nullable = false)
    private String phone;

    public User(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "roles=" + roles +
                ", userId='" + userId + '\'' +
                ", phone=" + phone +
                '}';
    }
}