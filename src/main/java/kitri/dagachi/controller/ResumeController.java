package kitri.dagachi.controller;

import kitri.dagachi.model.*;
import kitri.dagachi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/members/")
@RequiredArgsConstructor
public class ResumeController {

    private final MemberService memberService;

    @GetMapping("resume")
    public String resumeForm() {

        return "/members/resume";
    }

    @PostMapping("resume")
    public String resumeSet(@Valid ResumeForm form,
                            BindingResult bindingResult,
                            @AuthenticationPrincipal Member member) {

        System.out.println("form.getImage() : " + form.getImage());
        System.out.println("form.getGender() : " + form.getGender());
        System.out.println("form.getStack() : " + form.getStack());
        System.out.println("form.getCertificateName() : " + form.getCertificateName());
        System.out.println("form.getCertificateIssuer() : " + form.getCertificateIssuer());
        System.out.println("form.getIssuedDate() : " + form.getIssuedDate());
        System.out.println("form.getSchoolName() : " + form.getSchoolName());
        System.out.println("form.getMajorName() : " + form.getMajorName());
        System.out.println("form.getEntranceDate() : " + form.getEntranceDate());
        System.out.println("form.getGraduationDate() : "+ form.getGraduationDate());
        System.out.println("form.getEnterName() : " + form.getEnterName());
        System.out.println("form.getDuty() : " + form.getDuty());
        System.out.println("form.getRank() : " + form.getRank());
        System.out.println("form.getJoiningDate() : " + form.getJoiningDate());
        System.out.println("form.getLeavingDate() : " + form.getLeavingDate());
        System.out.println("form.getAwardAgency() : " + form.getAwardAgency());
        System.out.println("form.getAwardName() : " + form.getAwardName());
        System.out.println("form.getCompetitionName() : " + form.getCompetitionName());
        System.out.println("form.getAwardDate() : " + form.getAwardDate());

        PersonalInfo personalInfo = PersonalInfo.builder()
                .id(member.getId())
                .image(form.getImage())
                .gender(form.getGender())
                .stack(form.getStack())
                .build();

        MemberEducation memberEducation = MemberEducation.builder()
                .id(member.getId())
                .schoolName(form.getSchoolName())
                .majorName(form.getMajorName())
                .startDate(form.getEntranceDate())
                .endDate(form.getGraduationDate())
                .educationType(form.getEducationType())
                .build();

        MemberCeritifcates memberCeritifcates = MemberCeritifcates.builder()
                .id(member.getId())
                .certificateName(form.getCertificateName())
                .certificateIssuer(form.getCertificateIssuer())
                .issuedDate(form.getIssuedDate())
                .build();

        MemberCareer memberCareer = MemberCareer.builder()
                .id(member.getId())
                .enterName(form.getEnterName())
                .duty(form.getDuty())
                .rank(form.getRank())
                .startDate(form.getJoiningDate())
                .endDate(form.getLeavingDate())
                .build();

        MemberAwards memberAwards = MemberAwards.builder()
                .id(member.getId())
                .awardAgency(form.getAwardAgency())
                .awardName(form.getAwardName())
                .competitionName(form.getCompetitionName())
                .awardDate(form.getAwardDate())
                .build();

        return "redirect:/";
    }


}
