package kitri.dagachi.controller;

import kitri.dagachi.SessionConstants;
import kitri.dagachi.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {

    @GetMapping("/")    // HttpSession이 존재하면 session attribute에서 name이 SessionConstatns.LOGIN_MEMBER인 값을 가져와 loginMember와 바인딩
    public String home(@SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Member loginMember,
                       Model model) {
//         세션에 회원 데이터가 없으면 홈으로 이동
        if(loginMember == null) {
            System.out.println(loginMember);
            return "home";
        }

        // 세션이 유지되면 로그인 홈으로 이동
        if(loginMember.getROLE().equals("ROLE_PER")) {
//            model.addAttribute("member", loginMember);
            return "personal_home";
        }
        else if(loginMember.getROLE().equals("ROLE_ENT")) {
//            model.addAttribute("member", loginMember);
            return "enterprise_home";
        }

        return null;
    }

}
