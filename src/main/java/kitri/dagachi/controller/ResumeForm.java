package kitri.dagachi.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter @Setter
public class ResumeForm {

    // personal_info
    private Long id;

    private String addr;

    private String addrDetail;

    private String profile;

    private Character gender;

    private String stack;

    // member_education
    private String gradChk;

    private String schoolName;

    private String majorName;

    private String majorDetail;

    private String entranceDate;

    private String graduationDate;

    private String educationType;

    // member_certificates
    private String certificateName;

    private String certificateIssuer;

    private String issuedDate;

    // member_awards
    private String awardAgency;

    private String awardName;

    private String competitionName;

    private String awardDate;

    // member_career
    private String enterName;

    private String rank;

    private String dept;

    private String duty;

    private String reasonChk;

    private String joiningDate;

    private String leavingDate;


}
