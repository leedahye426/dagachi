package kitri.dagachi.controller;

import kitri.dagachi.model.*;
import kitri.dagachi.repository.MemberRepository;
import kitri.dagachi.repository.ResumeRepository;
import kitri.dagachi.service.MemberService;
import kitri.dagachi.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/members/")
@RequiredArgsConstructor
public class ResumeController {


    private final ResumeService resumeService;
    private final EntityManager em;
    private final ResumeRepository resumeRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;



    @GetMapping("resumeChk")
    public String resumeView(@AuthenticationPrincipal Member member,
                             Model model) {


        PersonalInfo personalInfo = resumeRepository.findById(member.getId());
        List<MemberEducation> memberEducations = resumeService.findAllEducation(member.getId());
        List<MemberCertificates> memberCertificates = resumeService.findAllCertificate(member.getId());
        List<MemberAwards> memberAwards = resumeService.findAllAward(member.getId());
        List<MemberCareers> memberCareers = resumeService.findAllCareer(member.getId());

        Member modelMember = memberRepository.findById(member.getId());
        System.out.println("modelMember.getAddr() : " + modelMember.getAddr());
        System.out.println("modelMember.getAddrDetail() : " + modelMember.getAddrDetail());

        model.addAttribute("member", modelMember);
        model.addAttribute("personalInfo", personalInfo);
        model.addAttribute("educations", memberEducations);
        model.addAttribute("certificates", memberCertificates);
        model.addAttribute("awards", memberAwards);
        model.addAttribute("careers", memberCareers);

        return "/members/resumeChk";
    }

    @GetMapping("resumeChk/{memberId}")
    public String resumeViewById(@PathVariable Long memberId,
                             Model model) {

        Member member = memberService.findOne(memberId);
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

        Member modelMember = memberRepository.findById(member.getId());
        System.out.println("modelMember.getAddr() : " + modelMember.getAddr());
        System.out.println("modelMember.getAddrDetail() : " + modelMember.getAddrDetail());

        model.addAttribute("member", modelMember);
        model.addAttribute("personalInfo", personalInfo);
        model.addAttribute("educations", memberEducations);
        model.addAttribute("certificates", memberCertificates);
        model.addAttribute("awards", memberAwards);
        model.addAttribute("careers", memberCareers);

        return "/members/resumeEdit";
    }

    @PostMapping("resumeEdit")
    public String resumeSet(@Valid ResumeForm form,
                            BindingResult bindingResult,
                            @AuthenticationPrincipal Member member,
                            @RequestParam("image") MultipartFile file,
                            @RequestParam("uploadFileName") String uploadFileName) throws IOException {

        String prevImage = null;

        // ???????????? ???
        try {
            if (resumeRepository.findById(member.getId()).getId() > 0L) {
                System.out.println("resumeRepository.findById(member.getId()).getId() : " + resumeRepository.findById(member.getId()).getId());
                System.out.println("?????? ???????????? ??????");
                prevImage = resumeRepository.findById(member.getId()).getImage();
                resumeService.deleteInfo(member.getId());
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }

        System.out.println("?????? ???????????? ??????");
        System.out.println("???????????? ??????");
        memberService.updateEmailById(member.getId(), form.getAddr(), form.getAddrDetail());
        System.out.println("???????????? ???");

        System.out.println("prevImage : " + prevImage);

        PersonalInfo personalInfo = PersonalInfo.builder()
                .id(member.getId())
                .image(prevImage)
                .stack(form.getStack())
                .build();

        String fileName = file.getOriginalFilename();
        System.out.println("fileName : " + fileName);
        System.out.println("file.getSize() : " + file.getSize());

        // ???????????? ??? ??? ????????????
        if(!fileName.equals("") && file.getSize() < 1048576L) {

            String uploadDir = "D:/test/profile/";

            // ?????? ?????? ??? ?????? ??????
            File delFile = new File(uploadDir + prevImage);
            delFile.delete();

            System.out.println("fileName : " + fileName);

            String uuid = UUID.randomUUID().toString();
            System.out.println("uuid : " + uuid);

            String savedName = uuid + "_" + fileName;
            File savedFile = new File(uploadDir, savedName);
            System.out.println("savedName : " + savedName);

            try {
                file.transferTo(savedFile);
                personalInfo.setImage(savedName);

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        resumeService.saveInfo(personalInfo);
        System.out.println("personalInfo.getImage() : " + personalInfo.getImage());

        System.out.println("member.getId() : " + member.getId());
//        System.out.println("form.getImage() : " + form.getImage());
        System.out.println("form.getGender() : " + form.getGender());
        System.out.println("form.getStack() : " + form.getStack());
        System.out.println("form.getCertificateName() : " + form.getCertificateName());
        System.out.println("form.getCertificateIssuer() : " + form.getCertificateIssuer());
        System.out.println("form.getIssuedDate() : " + form.getIssuedDate());
        System.out.println("form.getGradChk() : " + form.getGradChk());
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
        System.out.println("form.getAddr() : " + form.getAddr());
        System.out.println("form.getAddrDetail() : " + form.getAddrDetail());

        resumeRepository.deleteAllEducationById(member.getId());
        if (form.getSchoolName() != null) {
            for (int i = 0; i < (form.getSchoolName()).split(",").length; i++) {
                MemberEducation memberEducation = MemberEducation.builder()
                        .id(member.getId())
                        .gradChk(form.getGradChk().split(",")[i])
                        .schoolName(form.getSchoolName().split(",")[i])
//                        .majorName(form.getMajorName().split(",")[i])
//                        .majorDetail(form.getMajorDetail().split(",")[i])
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
//                        .rank(form.getRank().split(",")[i])
//                        .dept(form.getDept().split(",")[i])
                        .reasonChk(form.getReasonChk().split(",")[i])
//                        .duty(form.getDuty().split(",")[i])
                        .startDate(form.getJoiningDate().split(",")[i])
                        .endDate(form.getLeavingDate().split(",")[i])
                        .build();
                resumeService.saveCareer(memberCareers);
            }
        }
        return "redirect:/";
    }


}
