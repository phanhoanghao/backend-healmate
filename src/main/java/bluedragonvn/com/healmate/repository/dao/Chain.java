package bluedragonvn.com.healmate.repository.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: blued, Date: 3/6/2024
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CHAIN")
@Builder
public class Chain extends DateAudit{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "CHAIN_ID", updatable = false, nullable = false)
    private String id;

    @Column(name = "CHAIN_NAME", nullable = false)
    private String chainName;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "chains")
    @JsonIgnore
    private Set<Hospital> hospitals = new HashSet<>();

    @Column(name = "CHAIN_DESCRIPTION")
    private String chainDescription;

    public void addHos(Hospital hospital) {
        this.hospitals.add(hospital);
        hospital.getChains().add(this);
    }

    public void removeUser(Hospital hospital) {
        this.hospitals.remove(hospital);
        hospital.getChains().remove(this);
    }
}
