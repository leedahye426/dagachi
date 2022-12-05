package kitri.dagachi.controller;

import kitri.dagachi.SessionConstants;
import kitri.dagachi.model.Competition;
import kitri.dagachi.model.Member;
import kitri.dagachi.model.Post;
import kitri.dagachi.model.Project;
import kitri.dagachi.service.CompetitionService;
import kitri.dagachi.service.ProjectService;
import kitri.dagachi.service.postService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final CompetitionService competitionService;
    private  final ProjectService projectService;
    private  final postService postService;

    // 세션 세팅 V1
//    @GetMapping("/")    // HttpSession이 존재하면 session attribute에서 name이 SessionConstatns.LOGIN_MEMBER인 값을 가져와 loginMember와 바인딩
//    public String home(@SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Member loginMember,
//                       Model model) {

//                 세션에 회원 데이터가 없으면 홈으로 이동
//        if(loginMember == null) {
//            System.out.println(loginMember);
//            return "home";
//        }
//
//         세션이 유지되면 로그인 홈으로 이동
//        if(loginMember.getROLE().equals("ROLE_PER")) {
//            model.addAttribute("member", loginMember);
//            return "personal_home";
//        }
//        else if(loginMember.getROLE().equals("ROLE_ENT")) {
//            model.addAttribute("member", loginMember);
//            return "enterprise_home";
//        }
//        return "home";
//    }

    // 세션 세팅 V2
    @GetMapping("/")
    public String home(@AuthenticationPrincipal Member member,
                       Model model,
                       HttpSession httpSession,
                       HttpServletRequest request) {
        // 인증된 사용자 정보를 멤버 매핑
//        HttpSession hp = httpSession.setAttribute("log", member);

//        String referer = request.getHeader("referer");
//
//        System.out.println("referer : " + referer);

        if (member != null) model.addAttribute("loginMember", member);

        List<Competition> competitions = competitionService.findAllCompetition();

        Competition firstOne = competitionService.findFirst();

        List<Project> projects = projectService.findOrderByLike(3);
        List<Post> posts = postService.findOrderByLike(3);
//        for(Project p : projects) System.out.println(p.getProject_title() + ":" + p.getLike_cnt());
        model.addAttribute("competitions", competitions);
        model.addAttribute("posts", posts);
        model.addAttribute("projects", projects);
        model.addAttribute("firstOne", firstOne);
        return "home";
    }

    @GetMapping("/denied")
    public String denined() {

        System.out.println("deniedError");

        return "errorPage";
    }


}
