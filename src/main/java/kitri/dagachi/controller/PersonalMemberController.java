package kitri.dagachi.controller;

//import kitri.dagachi.SecurityConfig;
import kitri.dagachi.SessionConstants;
import kitri.dagachi.model.Member;
import kitri.dagachi.service.EmailService;
import kitri.dagachi.service.MemberService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@Log4j2
@Controller
@RequestMapping("/members/")
@RequiredArgsConstructor
public class PersonalMemberController {

    @Autowired
    private final MemberService memberService;
    private final EmailService emailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

        // 로그인 V1
//    @PostMapping("/login")
//    public String loginMatch(@Valid LoginForm form, BindingResult result) {
//
//        String email = form.getEmail();
//        String encPwd = bCryptPasswordEncoder.encode(form.getPasswd());
//        Long code = form.getCodeType();
//
////        memberService.loginMatch(email, encPwd);
//
//        return "redirect:/";
//    }

    @GetMapping("/join")
    public String joinMain(@SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Member loginMember) {

        if(loginMember != null) {
            return "redirect:/";
        }
        return "members/join";
    }

    @GetMapping("join/personal")
    public String personalForm(@SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Member loginMember) {

        if(loginMember != null) {
            return "redirect:/";
        }

        return "members/personal_join";
    }

    @PostMapping("join/personal")
    public String personalCreate(@Valid MemberForm form, BindingResult result) {

        // 현재 시간 구하기(가입 시간, ... ) -> DEFAULT SYSDATE 변경
        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime formatedNow = now.format(DateTimeFormatter.ofPattern("YYYY/MM/DD hh:mm:ss"));




        // 멤버 생성 V1
//        Member member = new Member();

//        member.setROLE("ROLE_PER"); // 개인회원 코드
//        member.setName(form.getName()); // 이름
//        member.setEmail(form.getEmail()); // 이메일(이메일 양식 유효성 검증 예정)


        // 패스워드 암호화
        String encPwd = bCryptPasswordEncoder.encode(form.getPassword());
//        member.setPassword(encPwd);
//        member.setPasswd(form.getPasswd());
        // 전화번호 칼럼 추가 해야됨
        // member.set
//        member.setJoinDate(now); // 생성일시 DB 저장용

        Member member = Member.builder() // Builder 이용 PER 멤버 생성 V2
                .name(form.getName())
                .email(form.getEmail())
                .password(encPwd)
                .ROLE("ROLE_PER")
                .joinDate(now)
                .build();

//        System.out.println("form.getName : " + form.getName());
//        System.out.println("form.getPasswd : " + form.getPassword());
//        System.out.println("form.getEmail : " + form.getEmail());
//
//        System.out.println("member.getId : " + member.getId());
//        System.out.println("member.getName : " + member.getName());
//        System.out.println("member.getPasswd : " + member.getPassword());
//        System.out.println("member.getCode : " + member.getCode());
//        System.out.println("member.getJoinDate : " + member.getJoinDate());
//        System.out.println("member.getEmail : " + member.getEmail());

        memberService.join(member);
        return "redirect:/";
    }

}
