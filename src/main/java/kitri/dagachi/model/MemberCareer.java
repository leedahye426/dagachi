package kitri.dagachi.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "member_career")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberCareer {

    @Id
    @GeneratedValue
    @Column(name = "row_num")
    private Long rowNum;

    @Column(name = "member_id")
    private Long id;

    @Column(name = "enter_name")
    private String enterName;

    @Column(name = "duty")
    private String duty;

    @Column(name = "rank")
    private String rank;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;
}
