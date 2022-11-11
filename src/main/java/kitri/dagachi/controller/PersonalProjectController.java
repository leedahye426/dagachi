package kitri.dagachi.controller;

import kitri.dagachi.model.Member;
import kitri.dagachi.model.Project;
import kitri.dagachi.service.MemberService;
import kitri.dagachi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PersonalProjectController {

    private final ProjectService projectService;
    private final MemberService memberService;
    @GetMapping("/project/personal_project_list")
    public String list(Model model) {
        List<Project> projects = projectService.findAllProjects();
        model.addAttribute("projects", projects);
        return "project/personal_project_list";
    }
    @GetMapping("/project/project_register")
    public String projectRegisterForm() {
        return "project/projectRegisterForm";
    }

    @PostMapping("/project/project_register")
    public String projectRegister(MultipartHttpServletRequest multiReq) throws IOException {
        String team_name = multiReq.getParameter("team_name");
        String project_title = multiReq.getParameter("project_title");
        String project_content = multiReq.getParameter("project_content");
        MultipartFile file = multiReq.getFile("file");
        String[] members_email = multiReq.getParameterValues("member_email");
        for(String email: members_email) System.out.println(email);

        projectService.register(file, team_name, project_title, project_content, members_email);

        return "redirect:/project/personal_project_list";


    }

    @GetMapping("/project/personal/{project_id}/detail")
    public String detailPage(@PathVariable("project_id") Long project_id, Model model){
        Project project = projectService.findProject(project_id);
        List<Member> project_members = memberService.findmembers(project_id);
        model.addAttribute("project", project);
        model.addAttribute("project_members", project_members);
        return "project/project_detail";
    }

    @GetMapping("/project/search")
    public String search(@RequestParam String keyword, Model model) {
        List<Project> projects = projectService.findProjects(keyword);
        model.addAttribute("projects", projects);

        return "project/personal_project_list";
    }

    @GetMapping("/project/personal/{project_id}/delete")
    public String delete(@PathVariable Long project_id) {
        projectService.deleteProject(project_id);
        return "redirect:/project/personal_project_list";
    }

}
