package kitri.dagachi.controller;

import kitri.dagachi.model.Member;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("members/join")
//@RequiredArgsConstructor
public class MemberController {

    @GetMapping
    public String joinMain() {
        return "members/join";
    }

    @GetMapping("/personal")
    public String personalForm() {
        return "members/personal_join";
    }


    // 현재 시간 구하기(가입 시간, ... )
    LocalDateTime now = LocalDateTime.now();
    String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

    @PostMapping("/personal")
    public String personalCreate(@NotNull MemberForm form) {

        Member member = new Member();
        member.setCode(1);
        member.setName(form.getName());
        member.setPasswd(form.getPasswd());
        member.setEmail(form.getEmail());
        // 전화번호 칼럼 추가 해야됨
        //        member.set
        member.setJoinDate(formatedNow);

        return "redirect:/";
    }
}
