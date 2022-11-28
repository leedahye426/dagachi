package kitri.dagachi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "personal_info")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalInfo {

    @Id
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_image")
    private String image;

    @Column(name = "member_gender")
    private Character gender;

    @Column(name = "member_stack")
    private String stack;

}
