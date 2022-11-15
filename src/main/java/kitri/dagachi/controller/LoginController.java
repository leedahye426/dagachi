package kitri.dagachi.controller;

import kitri.dagachi.SessionConstants;
import kitri.dagachi.model.Member;
import kitri.dagachi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("members/login")
    public String login(@SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Member loginMember) {

        if(loginMember != null) {
            return "redirect:/";
        }

        return "members/login";
    }

    @PostMapping("/members/login")
    public String login(@Valid LoginForm loginForm,
                        BindingResult bindingResult,
                        HttpServletRequest request) {

//        if(!memberService.loginMatch(loginForm.getEmail(), loginForm.getPasswd(), loginForm.getCodeType())
//        || bindingResult.hasErrors()) {
//            return "members/login";
//        }

        if(bindingResult.hasErrors()) {
            return "members/login";
        }

        Member loginMember = memberService.loginService(loginForm.getEmail(), loginForm.getPasswd(), loginForm.getCodeType());

//        System.out.println(loginMember);
//        System.out.println("loginMember.getEmail() : " + loginMember.getEmail());
//        System.out.println("loginMember.getCode() : " + loginMember.getCode());
//        System.out.println("loginMember.getId() : " + loginMember.getId());
//        System.out.println("loginMember.getJoinDate() : " + loginMember.getJoinDate());
//        System.out.println("loginMember.getName() : " + loginMember.getName());
//        System.out.println("loginMember.getAddr() : " + loginMember.getAddr());
//        System.out.println("loginMember.getPassword() : " + loginMember.getPassword());
//        System.out.println("loginMember.getBusinessNum() : " + loginMember.getBusinessNum());

        if(loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "members/login";
        }

        // 로그인 성공 처리
        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성하여 반환
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConstants.LOGIN_MEMBER, loginMember);

        System.out.println("홈으로");

        return "redirect:/";
    };

    @PostMapping("logout")
    public String logout(HttpServletRequest request) {

        // true일 경우 세션이 없으면 신규 세션 반환, false일 경우 세션이 없으면 그냥 null을 반환. 기본값 true
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

//    @PostMapping("/members/loginChk")
//    public boolean loginChk(@RequestParam("code") Long co)

}