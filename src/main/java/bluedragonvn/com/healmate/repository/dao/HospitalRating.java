package bluedragonvn.com.healmate.repository.dao;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: blued, Date: 3/6/2024
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HOS_RATING")
@Builder
public class HospitalRating {
    @Id
    @Column(name = "HOS_RATING_ID")
    private String hosRatingId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "HOS_ID", referencedColumnName = "HOS_ID")
    private Hospital hospital;

    @NotNull
    @NotBlank
    @Column(name = "AVG", updatable = true, nullable = true)
    private double avg;

    @Override
    public String toString() {
        return "HospitalRating{" +
                "Hos_id=" + hospital.getHosId() +
                ", avg='" + avg +
                '}';
    }
}
