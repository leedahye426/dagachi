package kitri.dagachi.controller;

import kitri.dagachi.model.Project;
import kitri.dagachi.service.ProjectService;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

@Controller
public class ProjectController {

    private final ProjectService projectService;
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping("/project/project_register")
    public String projectRegisterForm(Model model) {
        model.addAttribute("projectForm", new ProjectForm());
        return "project/projectRegisterForm";
    }

    @PostMapping("/project/project_register")
    public String projectRegister(MultipartHttpServletRequest multiReq) throws IOException {
        String team_name = multiReq.getParameter("team_name");
        String project_title = multiReq.getParameter("project_title");
        MultipartFile file = multiReq.getFile("file");

        projectService.register(file, team_name, project_title);
        return "project/project_detail";
    }

    @GetMapping("/project/personal_project")
    public String list(Model model) {
        List<Project> projects = projectService.findProjects();
        model.addAttribute("projects", projects);
        return "project/pro_per";
    }

    @GetMapping("/project/{project_id}/detail")
    public String detailPage(@PathVariable("project_id") Long project_id, Model model){
        model.addAttribute("detail", projectService.findProject(project_id));
        return "project/project_detail";
    }
}
