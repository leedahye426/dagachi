package kitri.dagachi.controller;

import kitri.dagachi.model.*;
import kitri.dagachi.service.MemberService;
import kitri.dagachi.service.ProjectLikeService;
import kitri.dagachi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EnterpriseProjectController {

    private final ProjectService projectService;
    private final MemberService memberService;

    private final ProjectLikeService projectLikeService;

    @GetMapping("/project/enterprise/project_list")
    public String list(Model model) {
        List<Project> projects = projectService.findAllProjects();
        model.addAttribute("projects", projects);
        return "project/project_list";
    }

    @GetMapping("/project/enterprise/{project_id}/detail")
    public String detailPage(@PathVariable("project_id") Long project_id, Model model, HttpSession session,
                             @AuthenticationPrincipal Member member){
        Project project = projectService.findProject(project_id);
        List<Member> project_members = memberService.findMembers(project_id);
        List<ProjectTag> project_tags = projectService.findTags(project_id);
        Long member_id = member.getId();
        Long cnt = projectLikeService.findLikeCnt(project_id, member_id);


        List<PersonalInfo> personalInfo = new ArrayList<>();
        for(Member m : project_members) {
            personalInfo.add(memberService.findInfo(m.getId()));
        }
        System.out.println("______________________________________________________________");
        for(PersonalInfo p : personalInfo) System.out.println(p.getId());
        System.out.println("cnt" + cnt);
        model.addAttribute("cnt",cnt);
        model.addAttribute("project", project);
        model.addAttribute("project_members", project_members);
        model.addAttribute("project_tags", project_tags);
        model.addAttribute("personalInfo", personalInfo);
        return "project/project_detail";
    }

    @GetMapping("/project/enterprise/search")
    public String search(@RequestParam String keyword, String[] tag, Model model) {
        List<Project> projects = projectService.findProjectsByKeywordTag(keyword,tag);
        //for(String t:tag) System.out.println(t);
        model.addAttribute("projects", projects);
        return "project/project_list";
    }

    @PostMapping("/project/enterprise/like/emptyToFill")
    @ResponseBody
    public String emptyToFill(@RequestParam String project_id, HttpSession session, Model model,  @AuthenticationPrincipal Member member) {
        Long member_id = member.getId();

        ProjectLike projectLike = new ProjectLike();
        projectLike.setProject_id(Long.parseLong(project_id));
        projectLike.setMember_id(member_id);
        projectService.updateCnt(Long.parseLong(project_id),1L);

        projectLikeService.save(projectLike);
        System.out.println("empty to fill");

//        Project project = projectService.findProject(Long.parseLong(project_id));
//        project.setLike_cnt(project.getLike_cnt()+1);
//
//        model.addAttribute("project", project);

        return "123";
    }
    @PostMapping("/project/enterprise/like/fillToEmpty")
    @ResponseBody
    public String fillToEmpty(@RequestParam String project_id, HttpSession session, Model model, @AuthenticationPrincipal Member member) {
        //이미 하트를 눌렀음
        System.out.println("이미 누른 하트");
        Long member_id = member.getId();
        System.out.println("like 컬럼 삭제");
        ProjectLike projectLike = projectLikeService.findLike(Long.parseLong(project_id),member_id);
        projectService.updateCnt(Long.parseLong(project_id),-1L);

        projectLikeService.deleteProjectLike(projectLike);

//        Project project = projectService.findProject(Long.parseLong(project_id));
//        project.setLike_cnt(project.getLike_cnt()-1);
//        model.addAttribute("project", project);


        return "";
    }

    @GetMapping("/project/enterprise/myLike")
    public String myLike(Model model, @AuthenticationPrincipal Member member) {
        Long member_id = member.getId();
        List<Project> projects = projectService.findProjectsById(member_id);
        model.addAttribute("projects", projects);
        for(Project p: projects) System.out.println(p);
        return "/project/project_list";
    }

}
