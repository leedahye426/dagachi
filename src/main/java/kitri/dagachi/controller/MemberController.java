package kitri.dagachi.controller;

//import kitri.dagachi.SecurityConfig;
import kitri.dagachi.model.Member;
import kitri.dagachi.service.EmailService;
import kitri.dagachi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PreUpdate;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("members/")
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private final MemberService memberService;
    private final EmailService emailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public String login() {
        return "members/login";
    }

    @PostMapping("/login")
    public String loginMatch(@Valid LoginForm form, BindingResult result) {

        String email = form.getEmail();
        String encPwd = bCryptPasswordEncoder.encode(form.getPasswd());
        int code = form.getCodeType();

//        memberService.loginMatch(email, encPwd);

        return "redirect:/";
    }

    @GetMapping("/join")
    public String joinMain() {
        return "members/join";
    }

    @GetMapping("join/personal")
    public String personalForm() {
        return "members/personal_join";
    }

    @PostMapping("join/personal")
    public String personalCreate(@Valid MemberForm form, BindingResult result) {

        // 현재 시간 구하기(가입 시간, ... )
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

        // 멤버 생성
        Member member = new Member();
        member.setCode(1); // 개인회원 코드
        member.setName(form.getName()); // 이름
        member.setEmail(form.getEmail()); // 이메일(이메일 양식 유효성 검증 예정)

        // 패스워드 암호화
        String encPwd = bCryptPasswordEncoder.encode(form.getPassword());
        member.setPassword(encPwd);
//        member.setPasswd(form.getPasswd());
        // 전화번호 칼럼 추가 해야됨
        // member.set
        member.setJoinDate(formatedNow); // 생성일시 DB 저장용

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
