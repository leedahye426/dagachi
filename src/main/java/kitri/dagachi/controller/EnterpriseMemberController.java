package kitri.dagachi.controller;

import kitri.dagachi.SessionConstants;
import kitri.dagachi.model.Member;
import kitri.dagachi.service.EmailService;
import kitri.dagachi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("members/")
@RequiredArgsConstructor
public class EnterpriseMemberController {

//    @Autowired
    private final MemberService memberService;
    private final EmailService emailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    @GetMapping("/login")
//    public String login() {
//        return "members/login";
//    }

    @GetMapping("join/enterprise")
    public String enterForm(@SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Member loginMember) {

        if(loginMember != null) {
            return "redirect:/";
        }

        return "members/enterprise_join";
    }

    @PostMapping("join/enterprise")
    public String enterCreate(@Valid MemberForm form, BindingResult result) {

        // 현재 시간 구하기(가입 시간, ... ) -> DEFAULT SYSDATE 변경
        LocalDateTime joinDate = LocalDateTime.now();
//        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

        // 멤버 생성
        Member member = new Member();
        member.setCode(2L); // 개인회원 코드
        member.setName(form.getName()); // 이름
        member.setEmail(form.getEmail()); // 이메일(이메일 양식 유효성 검증 예정)
        member.setBusinessNum(form.getBsNum());
        member.setAddr(form.getAddr());

        // 패스워드 암호화
        String encPwd = bCryptPasswordEncoder.encode(form.getPassword());
        member.setPassword(encPwd);
//        member.setPasswd(form.getPasswd());
        // 전화번호 칼럼 추가 해야됨
        // member.set
        member.setJoinDate(joinDate); // 생성일시 DB 저장용

        System.out.println("form.getName : " + form.getName());
        System.out.println("form.getPasswd : " + form.getPassword());
        System.out.println("form.getEmail : " + form.getEmail());
        System.out.println("form.getBsNum : " + form.getBsNum());
        System.out.println("form.getAddr : " + form.getAddr());

        System.out.println("member.getId : " + member.getId());
        System.out.println("member.getName : " + member.getName());
        System.out.println("member.getPasswd : " + member.getPassword());
        System.out.println("member.getCode : " + member.getCode());
        System.out.println("member.getJoinDate : " + member.getJoinDate());
        System.out.println("member.getEmail : " + member.getEmail());

        memberService.join(member);
        return "redirect:/";
    }

}
