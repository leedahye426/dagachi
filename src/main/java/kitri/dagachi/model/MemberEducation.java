package kitri.dagachi.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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

    @Column(name = "education_type")
    private String educationType;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "school_graduate")
    private String gradChk;

    @Column(name = "major_name")
    private String majorName;

    @Column(name = "major_detail")
    private String majorDetail;




}
