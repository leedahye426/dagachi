package kitri.dagachi.controller;

import kitri.dagachi.service.EmailService;
import kitri.dagachi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String enterForm() {
        return "members/enterprise_join";
    }

}
