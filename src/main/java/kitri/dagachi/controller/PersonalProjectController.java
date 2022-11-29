package kitri.dagachi.controller;

import kitri.dagachi.model.Member;
import kitri.dagachi.model.Project;
import kitri.dagachi.model.ProjectTag;
import kitri.dagachi.service.MemberService;
import kitri.dagachi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PersonalProjectController {

    private final ProjectService projectService;
    private final MemberService memberService;

    @GetMapping("/project/personal/project_list")
    public String list(Model model) {
        List<Project> projects = projectService.findApprovedProject();
        model.addAttribute("projects", projects);
        return "/project/project_list";
    }
    @GetMapping("/project/personal/project_register_form")
    public String projectRegisterForm() {
        return "project/project_register_form";
    }

    @PostMapping("/project/personal/project_register_form")
    public String projectRegister(MultipartHttpServletRequest multiReq, @AuthenticationPrincipal Member member) throws IOException {
        String team_name = multiReq.getParameter("team_name");
        String project_title = multiReq.getParameter("project_title");
        String project_content = multiReq.getParameter("project_content");
        MultipartFile file = multiReq.getFile("file");

        String[] members_email = multiReq.getParameterValues("member_email");
        System.out.println("**********email*********");
        for(String email: members_email) System.out.println(email);

        String[] tags = multiReq.getParameterValues("tag");
        for(String tag : tags) System.out.println(tag);

        Long member_id = member.getId();
        projectService.register(file, team_name, project_title, project_content, members_email, tags, member_id);

        return "redirect:/project/personal/project_list";


    }

    @GetMapping("/project/personal/{project_id}/detail")
    public String detailPage(@PathVariable("project_id") Long project_id, Model model,  @AuthenticationPrincipal Member member){
        Project project = projectService.findProject(project_id);
        List<Member> project_members = memberService.findMembers(project_id);
        List<ProjectTag> project_tags = projectService.findTags(project_id);
        Long loginId = member.getId();
        model.addAttribute("loginId", loginId);
        model.addAttribute("project", project);
        model.addAttribute("project_members", project_members);
        model.addAttribute("project_tags", project_tags);
        return "project/project_detail";
    }

    @GetMapping("/project/personal/search")
    public String search(@RequestParam(required = false)  String keyword, @RequestParam(required = false)  String[] tag, Model model) {
        List<Project> projects = projectService.findProjectsByKeywordTag(keyword,tag);
        model.addAttribute("projects", projects);
        return "project/project_list";
    }

    @GetMapping("/project/personal/{project_id}/delete")
    public String delete(@PathVariable Long project_id) {
        projectService.deleteProject(project_id);
        return "redirect:/project/personal/project_list";
    }

    @GetMapping("/project/personal/{project_id}/update")
    public String update(@PathVariable Long project_id,Model model) {
        Project project = projectService.findProject(project_id);
        List<Member> members = memberService.findMembers(project_id);
        List<ProjectTag> tags = projectService.findTags(project_id);

        List<String> projectTags = new ArrayList<>();
        for(ProjectTag tag: tags) projectTags.add(tag.getProject_tag());

        for(Member m: members) System.out.println(m.getEmail());

        model.addAttribute("project", project);
        model.addAttribute("members", members);
        model.addAttribute("projectTags", projectTags);

        return "project/project_update_form";
    }

    @PostMapping("project/personal/{project_id}/update")
    public String updateRegister(@PathVariable Long project_id, MultipartHttpServletRequest multiReq) throws Exception{
        String team_name = multiReq.getParameter("team_name");
        String project_title = multiReq.getParameter("project_title");
        String project_content = multiReq.getParameter("project_content");
        MultipartFile file = multiReq.getFile("file");

        Project project = projectService.findProject(project_id);
        project.setTeam_name(team_name);
        project.setProject_title(project_title);
        project.setProject_content(project_content);

        String[] members_email = multiReq.getParameterValues("member_email");
        System.out.println("**********email*********");
        for(String email: members_email) System.out.println(email);

        String[] tags = multiReq.getParameterValues("tag");
        for(String tag : tags) System.out.println(tag);

        projectService.update(file, project, members_email, tags);
        return "redirect:/project/personal/project_list";
    }


    @PostMapping("/project/personal/email_check")
    @ResponseBody
    public ResponseEntity<?> emailCheck(String email) {
        String checkedEmail = memberService.findOneByEmail(email).getEmail();
        if(checkedEmail.equals(email)) return new ResponseEntity<>(checkedEmail, HttpStatus.OK);
        else return new ResponseEntity<>("등록된 이메일이 아닙니다.", HttpStatus.SERVICE_UNAVAILABLE);
    }

}
