package kitri.dagachi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {

    @GetMapping("/project/personal_project")
    public String project() {
        return "pro_per";
    }
}
