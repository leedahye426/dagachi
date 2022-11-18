package kitri.dagachi.controller;

import kitri.dagachi.SessionConstants;
import kitri.dagachi.model.Member;
import kitri.dagachi.model.Project;
import kitri.dagachi.model.ProjectLike;
import kitri.dagachi.model.ProjectTag;
import kitri.dagachi.service.MemberService;
import kitri.dagachi.service.ProjectLikeService;
import kitri.dagachi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static kitri.dagachi.SessionConstants.LOGIN_MEMBER;

@Controller
@RequiredArgsConstructor
public class EnterpriseProjectController {

    private final ProjectService projectService;
    private final MemberService memberService;

    private final ProjectLikeService projectLikeService;

    @GetMapping("/project/enterprise_project_list")
    public String list(Model model) {
        List<Project> projects = projectService.findAllProjects();
        model.addAttribute("projects", projects);


        return "project/enterprise_project_list";
    }

    @GetMapping("/project/enterprise/{project_id}/detail")
    public String detailPage(@PathVariable("project_id") Long project_id, Model model, HttpSession session){
        Project project = projectService.findProject(project_id);
        List<Member> project_members = memberService.findmembers(project_id);
        List<ProjectTag> project_tags = projectService.findTags(project_id);
        Long member_id = (Long)((Member)session.getAttribute(SessionConstants.LOGIN_MEMBER)).getId();

        Long cnt = projectLikeService.findLikeCnt(project_id, member_id);
        System.out.println("cnt" + cnt);
        model.addAttribute("cnt",cnt);
        model.addAttribute("project", project);
        model.addAttribute("project_members", project_members);
        model.addAttribute("project_tags", project_tags);
        return "project/enterprise_project_detail";
    }

    @GetMapping("/project/enterprise/search")
    public String search(@RequestParam String keyword, String[] tag, Model model) {
        List<Project> projects = projectService.findProjectsByKeywordTag(keyword,tag);
        //for(String t:tag) System.out.println(t);
        model.addAttribute("projects", projects);
        return "project/personal_project_list";
    }

    @PostMapping("/project/enterprise/like/emptyToFill")
    public String emptyToFill(@RequestParam String project_id, HttpSession session, Model model) {
        Long member_id = (Long)((Member)session.getAttribute(SessionConstants.LOGIN_MEMBER)).getId();

        ProjectLike projectLike = new ProjectLike();
        projectLike.setProject_id(Long.parseLong(project_id));
        projectLike.setMember_id(member_id);
        projectLikeService.save(projectLike);
        System.out.println("empty to fill");

        Project project = projectService.findProject(Long.parseLong(project_id));
        model.addAttribute("project", project);

        return "/project/enterprise_project_detail";
    }
    @PostMapping("/project/enterprise/like/fillToEmpty")
    public String fillToEmpty(@RequestParam String project_id, HttpSession session, Model model) {
        //이미 하트를 눌렀음
        System.out.println("이미 누른 하트");
        Long member_id = (Long)((Member)session.getAttribute(SessionConstants.LOGIN_MEMBER)).getId();
        System.out.println("like 컬럼 삭제");
        ProjectLike projectLike = projectLikeService.findLike(Long.parseLong(project_id),member_id);
        projectLikeService.deleteProjectLike(projectLike);

        Project project = projectService.findProject(Long.parseLong(project_id));
        model.addAttribute("project", project);

        return "/project/enterprise_project_detail";
    }


}
