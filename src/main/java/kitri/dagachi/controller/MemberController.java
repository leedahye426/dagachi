package kitri.dagachi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("/personal")
    public String personalCreate(MemberForm form) {

        return "redirect:/";
    }
}
