package bluedragonvn.com.healmate.repository.dao;

import bluedragonvn.com.healmate.ulti.StringListConverter;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: blued, Date: 3/6/2024
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REVIEW")
@Builder
public class Review extends DateAudit{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "REV_ID", updatable = false, nullable = false)
    private String revId;
    @NotNull
    @NotBlank
    @Column(name = "PHONE", updatable = false, nullable = false)
    private String phone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "HOS_ID", referencedColumnName = "HOS_ID")
    private Hospital hospital;
    @NotNull
    @NotBlank
    @Column(name = "CONTENT", updatable = false, nullable = false)
    private String content;
    @NotNull
    @NotBlank
    @Column(name = "RATING", updatable = false, nullable = false)
    private Integer rating;
    @Convert(converter = StringListConverter.class)
    @Column(name = "IMAGES", updatable = false, nullable = true)
    private List<String> images = new ArrayList<>();
    @Override
    public String toString() {
        return "Review{" +
                "phone=" + phone +
                ", hospital_name='" + hospital.getHos_name() + '\'' +
                ", hospital_id=" + hospital.getHosId() + '\'' +
                ", content=" + content + '\'' +
                ", rating=" + rating + '\'' +
                ", images=" + images +
                '}';
    }
}
