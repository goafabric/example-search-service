package org.goafabric.example.searchservice.persistence;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="person")
public class PersonBo {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @NonNull
    private AddressBo address;

    @OneToMany(mappedBy = "myPerson", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @NonNull
    private List<SkillBo> skills;

    //what a shoehorn of horseshit is this ?!?!? i have to do this myself ?!
    public void setSkills(List<SkillBo> skills) {
        if (skills != null) {
            skills.stream().forEach(skill -> skill.setMyPerson(this));
            this.skills = skills;
        }
    }
}

