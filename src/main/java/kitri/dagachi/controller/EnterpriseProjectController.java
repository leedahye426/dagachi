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
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class EnterpriseProjectController {

    private final ProjectService projectService;
    private final MemberService memberService;

    private final ProjectLikeService projectLikeService;

    @GetMapping("/project/enterprise/project_list")
    public String list(Model model) {
        List<Project> projects = projectService.findApprovedProject();
        model.addAttribute("projects", projects);
        return "project/project_list";
    }

    @GetMapping("/project/enterprise/detail/{project_id}")
    public String detailPage(@PathVariable("project_id") Long project_id, Model model, HttpSession session,
                             @AuthenticationPrincipal Member member){
        Project project = projectService.findProject(project_id);
        List<Member> project_members = memberService.findMembers(project_id);
        List<ProjectTag> project_tags = projectService.findTags(project_id);
        Long member_id = member.getId();
        Long cnt = projectLikeService.findLikeCnt(project_id, member_id);


        List<PersonalInfo> personalInfo = new ArrayList<>();
        for(Member m : project_members) {
            Optional<PersonalInfo> p = memberService.findInfo(m.getId());
            if(p.isPresent()) personalInfo.add(p.get());
            else {
                PersonalInfo noProfile = new PersonalInfo();
                noProfile.setId(m.getId());
                personalInfo.add(noProfile);
            }
        }
        System.out.println("______________________________________________________________");

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
        //?????? ????????? ?????????
        System.out.println("?????? ?????? ??????");
        Long member_id = member.getId();
        System.out.println("like ?????? ??????");
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
