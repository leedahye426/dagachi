package kitri.dagachi.controller;

import kitri.dagachi.model.Member;
import kitri.dagachi.model.Project;
import kitri.dagachi.service.MemberService;
import kitri.dagachi.service.ProjectService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final MemberService memberService;

    @GetMapping("/project/project_register")
    public String projectRegisterForm(Model model) {
        model.addAttribute("projectForm", new ProjectForm());
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

        return "redirect:/project/personal_project";


    }

    @GetMapping("/project/personal_project")
    public String list(Model model) {
        List<Project> projects = projectService.findAllProjects();
        model.addAttribute("projects", projects);
        return "project/pro_per";
    }

    @GetMapping("/project/{project_id}/detail")
    public String detailPage(@PathVariable("project_id") Long project_id, Model model){
        Project project = projectService.findProject(project_id);
        List<Member> project_members = memberService.findmembers(project_id);
        model.addAttribute("project", project);
        model.addAttribute("project_members", project_members);
        return "project/project_detail";
    }
}
