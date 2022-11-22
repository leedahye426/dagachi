package kitri.dagachi.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "member_education")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberEducation {

    @Id
    @GeneratedValue
    @Column(name = "row_num")
    private Long rowNum;

    @Column(name = "member_id")
    private Long id;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "major_name")
    private String majorName;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "education_type")
    private String educationType;
}
