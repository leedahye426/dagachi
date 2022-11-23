package kitri.dagachi.controller;

import kitri.dagachi.model.*;
import kitri.dagachi.repository.ResumeRepository;
import kitri.dagachi.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/members/")
@RequiredArgsConstructor
public class ResumeController {


    private final ResumeService resumeService;
    private final EntityManager em;
    private final ResumeRepository resumeRepository;


    @GetMapping("resumeChk")
    public String resumeView(@AuthenticationPrincipal Member member,
                             Model model) {


        PersonalInfo personalInfo = resumeRepository.findById(member.getId());
        List<MemberEducation> memberEducations = resumeService.findAllEducation(member.getId());
        List<MemberCertificates> memberCertificates = resumeService.findAllCertificate(member.getId());
        List<MemberAwards> memberAwards = resumeService.findAllAward(member.getId());
        List<MemberCareers> memberCareers = resumeService.findAllCareer(member.getId());

        model.addAttribute("member", member);
        model.addAttribute("personalInfo", personalInfo);
        model.addAttribute("educations", memberEducations);
        model.addAttribute("certificates", memberCertificates);
        model.addAttribute("awards", memberAwards);
        model.addAttribute("careers", memberCareers);

        return "/members/resumeChk";
    }

    @GetMapping("resumeEdit")
    public String resumeForm(@AuthenticationPrincipal Member member,
                             Model model) {

        PersonalInfo personalInfo = resumeRepository.findById(member.getId());
        List<MemberEducation> memberEducations = resumeService.findAllEducation(member.getId());
        List<MemberCertificates> memberCertificates = resumeService.findAllCertificate(member.getId());
        List<MemberAwards> memberAwards = resumeService.findAllAward(member.getId());
        List<MemberCareers> memberCareers = resumeService.findAllCareer(member.getId());

        model.addAttribute("member", member);
        model.addAttribute("personalInfo", personalInfo);
        model.addAttribute("educations", memberEducations);
        model.addAttribute("certificates", memberCertificates);
        model.addAttribute("awards", memberAwards);
        model.addAttribute("careers", memberCareers);

        return "/members/resume";
    }

    @PostMapping("resumeEdit")
    public String resumeSet(@Valid ResumeForm form,
                            BindingResult bindingResult,
                            @AuthenticationPrincipal Member member) {

        System.out.println("member.getId() : " + member.getId());
        System.out.println("form.getImage() : " + form.getImage());
        System.out.println("form.getGender() : " + form.getGender());
        System.out.println("form.getStack() : " + form.getPhoneNum());
        System.out.println("form.getCertificateName() : " + form.getCertificateName());
        System.out.println("form.getCertificateIssuer() : " + form.getCertificateIssuer());
        System.out.println("form.getIssuedDate() : " + form.getIssuedDate());
        System.out.println("form.getSchoolName() : " + form.getSchoolName());
        System.out.println("form.getMajorName() : " + form.getMajorName());
        System.out.println("form.getMajorDetail() : " + form.getMajorDetail());
        System.out.println("form.getEntranceDate() : " + form.getEntranceDate());
        System.out.println("form.getGraduationDate() : " + form.getGraduationDate());
        System.out.println("form.getEnterName() : " + form.getEnterName());
        System.out.println("form.getDuty() : " + form.getDuty());
        System.out.println("form.getRank() : " + form.getRank());
        System.out.println("form.getJoiningDate() : " + form.getJoiningDate());
        System.out.println("form.getLeavingDate() : " + form.getLeavingDate());
        System.out.println("form.getEducationType() : " + form.getEducationType());
        System.out.println("form.getAwardAgency() : " + form.getAwardAgency());
        System.out.println("form.getAwardName() : " + form.getAwardName());
        System.out.println("form.getCompetitionName() : " + form.getCompetitionName());
        System.out.println("form.getAwardDate() : " + form.getAwardDate());

        if (resumeRepository.findById(member.getId()).getId() > 0) {
            System.out.println("멤버 데이터가 있음");
            resumeService.deleteInfo(member.getId());
        }
        PersonalInfo personalInfo = PersonalInfo.builder()
                .id(member.getId())
                .image(form.getImage())
                .gender(form.getGender())
                .stack(form.getPhoneNum())
                .build();
        resumeService.saveInfo(personalInfo);

        resumeRepository.deleteAllEducationById(member.getId());
        if (form.getSchoolName() != null) {
            for (int i = 0; i < (form.getSchoolName()).split(",").length; i++) {
                MemberEducation memberEducation = MemberEducation.builder()
                        .id(member.getId())
                        .schoolName(form.getSchoolName().split(",")[i])
                        .majorName(form.getMajorName().split(",")[i])
                        .majorDetail(form.getMajorDetail().split(",")[i])
                        .startDate(form.getEntranceDate().split(",")[i])
                        .endDate(form.getGraduationDate().split(",")[i])
                        .educationType(form.getEducationType().split(",")[i])
                        .build();
                resumeService.saveEducation(memberEducation);
            }
        }

        resumeRepository.deleteAllCertificateById(member.getId());
        if (form.getCertificateName() != null) {
            for (int i = 0; i < form.getCertificateName().split(",").length; i++) {
                MemberCertificates memberCertificates = MemberCertificates.builder()
                        .id(member.getId())
                        .certificateName(form.getCertificateName().split(",")[i])
                        .certificateIssuer(form.getCertificateIssuer().split(",")[i])
                        .issuedDate(form.getIssuedDate().split(",")[i])
                        .build();
                resumeService.saveCertificates(memberCertificates);
            }
        }

        resumeRepository.deleteAllAwardById(member.getId());
        if (form.getAwardName() != null) {
            for (int i = 0; i < form.getAwardName().split(",").length; i++) {
                MemberAwards memberAwards = MemberAwards.builder()
                        .id(member.getId())
                        .awardAgency(form.getAwardAgency().split(",")[i])
                        .awardName(form.getAwardName().split(",")[i])
//                        .competitionName(form.getCompetitionName().split(",")[i])
                        .awardDate(form.getAwardDate().split(",")[i])
                        .build();
                resumeService.saveAwards(memberAwards);
            }
        }

        resumeRepository.deleteAllCareerById(member.getId());
        if (form.getEnterName() != null) {
            for (int i = 0; i < form.getEnterName().split(",").length; i++) {
                MemberCareers memberCareers = MemberCareers.builder()
                        .id(member.getId())
                        .enterName(form.getEnterName().split(",")[i])
                        .duty(form.getDuty().split(",")[i])
                        .rank(form.getRank().split(",")[i])
                        .startDate(form.getJoiningDate().split(",")[i])
                        .endDate(form.getLeavingDate().split(",")[i])
                        .build();
                resumeService.saveCareer(memberCareers);
            }
        }
        return "redirect:/";
    }


}
