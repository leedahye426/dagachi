package kitri.dagachi.controller;

import kitri.dagachi.model.Project;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProjectController {

    @GetMapping("/project/personal_project")
    public String project(Model model) {

        return "project/pro_per";
    }

    @GetMapping("/project/project_register")
    public String projectRegisterForm() {
        return "project/projectRegisterForm";
    }
}
