package kitri.dagachi.controller;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter @Setter
public class ResumeForm {

    // personal_info
    private Long id; // member_id <- join으로 가져오기

    private String image;

    private Character gender;

    private String stack;

    // member_education
    private String schoolName;

    private String majorName;

    private LocalDateTime entranceDate;

    private LocalDateTime graduationDate;

    private String educationType;

    // member_certificates
    private String certificateName;

    private String certificateIssuer;

    private LocalDateTime issuedDate;

    // member_career
    private String enterName;

    private String duty;

    private String rank;

    private LocalDateTime joiningDate;

    private LocalDateTime leavingDate;

    // member_awards
    private String awardAgency;

    private String awardName;

    private String competitionName;

    private LocalDateTime awardDate;
}
