package bluedragonvn.com.healmate.repository.dao;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author: blued, Date: 3/6/2024
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HOS", uniqueConstraints = {
        @UniqueConstraint(columnNames = "PHONE"),
        @UniqueConstraint(columnNames = "HOS_NAME")
})
@Builder
public class Hospital extends DateAudit{
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.DETACH)
    @JoinTable(name = "HOS_CHAINS",
            joinColumns = {@JoinColumn(name = "HOS_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CHAIN_ID")})
    Set<Chain> chains;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "HOS_ID", updatable = false, nullable = false)
    private String hosId;
    @NotNull
    @NotBlank
    @Column(name = "HOS_NAME", updatable = false, nullable = false)
    private String hos_name;
    @NotNull
    @NotBlank
    @Column(name = "DIRECTOR", updatable = true, nullable = false)
    private String director;
    @NotNull
    @NotBlank
    @Column(name = "PHONE", updatable = false, nullable = false)
    private String phone;
    @Column(name = "ADDR", updatable = true, nullable = true)
    private String addr;
    @NotNull
    @NotBlank
    @Column(name = "GEO", updatable = true, nullable = true)
    private String geo;

    @OneToOne(mappedBy = "hospital")
    private Review review;

    @OneToOne(mappedBy = "hospital")
    private HospitalRating hospitalRating;
    
    @OneToOne(optional = false)
    @JoinColumn(name = "USER_ID", unique = true)
    private User user;

    public Hospital(String hos_name, String director, String phone, String addr, String geo) {
        this.hos_name = hos_name;
        this.director = director;
        this.phone = phone;
        this.addr = addr;
        this.geo = geo;
    }

    public void findGeo(Hospital hospital) {
        if(hospital.getGeo()==null) {
            // cần thêm chức năng tính tọa độ dựa trên address và thông tin điện thoại của bệnh viện
        }
    }
    @Override
    public String toString() {
        return "Hospital{" +
                "chain=" + chains +
                ", hosId='" + hosId + '\'' +
                ", hosName='" + hos_name + '\'' +
                ", director='" + director + '\'' +
                ", addr='" + addr + '\'' +
                ", geo='" + geo + '\'' +
                ", phone=" + phone +
                '}';
    }
}
