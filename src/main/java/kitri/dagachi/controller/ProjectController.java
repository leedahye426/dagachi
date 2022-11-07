package kitri.dagachi.controller;

import kitri.dagachi.model.Project;
import kitri.dagachi.service.ProjectService;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProjectController {

    private final ProjectService projectService;
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

//    @GetMapping("/project/personal_project")
//    public String project(Model model) {
//
//        return "project/pro_per";
//    }

    @GetMapping("/project/project_register")
    public String projectRegisterForm(Model model) {
        model.addAttribute("projectForm", new ProjectForm());
        return "project/projectRegisterForm";
    }

    @PostMapping("/project/project_register")
    public String projectRegister(ProjectForm projectForm) {

//        Project project = new Project(projectForm.getTeam_name());
        return "project/pro_per";
    }

    @GetMapping("/project/personal_project")
    public String list(Model model) {
        List<Project> projects = projectService.findProjects();
        model.addAttribute("projects", projects);
        return "project/pro_per";
    }

    @GetMapping("/project/{project_id}/detail")
    public String detailPage(@PathVariable("project_id") Long project_id, Model model){

        return "project/project_detail";
    }
}
