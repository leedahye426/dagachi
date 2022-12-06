package kitri.dagachi.controller;

import kitri.dagachi.model.Member;
import kitri.dagachi.repository.MemberRepository;
import kitri.dagachi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/members/login")
    public String login(@AuthenticationPrincipal Member loginMember,
                        HttpServletRequest request) {
        System.out.println("로그인페이지입니다");

        if(loginMember != null) {
            return "redirect:/";
        }
        // 요청 시점의 사용자 URI 정보를 Session의 Attribute에 담아서 전달(잘 지워줘야 함)
        // 로그인이 틀려서 다시 하면 요청 시점의 URI가 로그인 페이지가 되므로 조건문 설정
        String uri = request.getHeader("Referer");
        try {
            if(!uri.contains("/login")) {
                request.getSession().setAttribute("prevPage", request.getHeader("Referer"));
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }

        return "members/login";
    }

    @PostMapping("/members/login")
    public String login(@Valid LoginForm loginForm,
                        BindingResult bindingResult,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Authentication authentication) throws IOException, ServletException {

//        if(!memberService.loginMatch(loginForm.getEmail(), loginForm.getPasswd(), loginForm.getCodeType())
//        || bindingResult.hasErrors()) {
//            return "members/login";
//        }

//        if(bindingResult.hasErrors()) {
//            return "members/login";
//        }
//
//        System.out.println("loginMember.getEmail() : " + loginForm.getEmail());
//        System.out.println("loginMember.getPassword() : " + loginForm.getPasswd());
//        System.out.println("loginMember.getROLE() : " + loginForm.getROLE());
//
//        Member loginMember = memberService.loginService(loginForm.getEmail(), loginForm.getPasswd(), loginForm.getROLE());
//        System.out.println("loginMember: " + loginMember);
//        System.out.println("loginMember.getEmail() : " + loginMember.getEmail());
//        System.out.println("loginMember.getPassword() : " + loginMember.getPassword());
//        System.out.println("loginMember.getROLE() : " + loginMember.getROLE());

//        System.out.println(loginMember);
//        System.out.println("loginMember.getEmail() : " + loginMember.getEmail());
//        System.out.println("loginMember.getCode() : " + loginMember.getCode());
//        System.out.println("loginMember.getId() : " + loginMember.getId());
//        System.out.println("loginMember.getJoinDate() : " + loginMember.getJoinDate());
//        System.out.println("loginMember.getName() : " + loginMember.getName());
//        System.out.println("loginMember.getAddr() : " + loginMember.getAddr());
//        System.out.println("loginMember.getPassword() : " + loginMember.getPassword());
//        System.out.println("loginMember.getBusinessNum() : " + loginMember.getBusinessNum());

//        if(loginMember == null) {
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
//            return "members/login";
//        }

        // 로그인 성공 처리
        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성하여 반환
//        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보 보관
//        session.setAttribute(SessionConstants.LOGIN_MEMBER, loginMember);

        System.out.println("홈으로");

        return "redirect:/";
    };

    @GetMapping("/members/pwdChange")
    public String pwdChange() {
        return "/members/pwdChange";
    }

    @PostMapping("/members/pwdChange")
    @ResponseBody
    public String pwdChange(@RequestParam("prevPwd") String prevPwd,
                            @RequestParam("newPwd") String newPwd,
                            @RequestParam("newPwdChk") String newPwdChk,
                            @AuthenticationPrincipal Member authMember) throws IOException {



        System.out.println("prevPwd : " + prevPwd);
        System.out.println("newPwd : " + newPwd);
        System.out.println("newPwdChk : " + newPwdChk);

        String newPwdPattern = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$";

        // 현재 비밀번호 일치 여부
        if(!bCryptPasswordEncoder.matches(prevPwd, authMember.getPassword())) {
            return "notPrevPwd";
        }
        else if(!Pattern.matches(newPwdPattern, newPwd)) {
            return "notPwdPattern";
        }
        else if(bCryptPasswordEncoder.matches(newPwd, authMember.getPassword())) {
            return "samePwd";
        }
        else if(!bCryptPasswordEncoder.matches(newPwd, authMember.getPassword()) && !newPwd.equals(newPwdChk)) {
            return "notSameNewPwd";
        }
        else if(!bCryptPasswordEncoder.matches(newPwd, authMember.getPassword()) && newPwd.equals(newPwdChk)) {

            String encryptNewPwd = bCryptPasswordEncoder.encode(newPwd);
            System.out.println("encryptNewPwd : " + encryptNewPwd);
            memberRepository.updatePasswordByEmail(authMember.getEmail(), encryptNewPwd);
            System.out.println("prevPwd : " + prevPwd);
            System.out.println("newPwd : " + newPwd);
            System.out.println("newPwdChk : " + newPwdChk);

        }

        return "sameNewPwd";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        // true일 경우 세션이 없으면 신규 세션 반환, false일 경우 세션이 없으면 그냥 null을 반환. 기본값 true
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }

        return "redirect:/";
    }


}
