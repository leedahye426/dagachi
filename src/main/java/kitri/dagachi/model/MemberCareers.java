package kitri.dagachi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "member_career")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberCareers {

    @Id
    @GeneratedValue
    @Column(name = "row_num")
    private Long rowNum;

    @Column(name = "member_id")
    private Long id;

    @Column(name = "enter_name")
    private String enterName;

    @Column(name = "rank")
    private String rank;

    @Column(name = "duty")
    private String duty;

    @Column(name = "dept")
    private String dept;

    @Column(name = "reason")
    private String reasonChk;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;
}
