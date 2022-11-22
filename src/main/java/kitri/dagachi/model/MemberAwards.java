package kitri.dagachi.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "member_awards")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberAwards {

    @Id
    @GeneratedValue
    @Column(name = "row_num")
    private Long rowNum;

    @Column(name = "member_id")
    private Long id;

    @Column(name = "award_agency")
    private String awardAgency;

    @Column(name = "award_name")
    private String awardName;

    @Column(name = "competition_name")
    private String competitionName;

    @Column(name = "award_date")
    private LocalDateTime awardDate;
}
