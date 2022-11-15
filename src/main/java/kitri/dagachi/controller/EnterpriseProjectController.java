package kitri.dagachi.controller;

import kitri.dagachi.model.Member;
import kitri.dagachi.model.Project;
import kitri.dagachi.model.ProjectTag;
import kitri.dagachi.service.MemberService;
import kitri.dagachi.service.ProjectService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class EnterpriseProjectController {

    private final ProjectService projectService;
    private final MemberService memberService;

    @GetMapping("/project/enterprise_project_list")
    public String list(Model model) {
        List<Project> projects = projectService.findAllProjects();
        model.addAttribute("projects", projects);
        return "project/enterprise_project_list";
    }

    @GetMapping("/project/enterprise/{project_id}/detail")
    public String detailPage(@PathVariable("project_id") Long project_id, Model model){
        Project project = projectService.findProject(project_id);
        List<Member> project_members = memberService.findmembers(project_id);
        List<ProjectTag> project_tags = projectService.findTags(project_id);
        model.addAttribute("project", project);
        model.addAttribute("project_members", project_members);
        model.addAttribute("project_tags", project_tags);
        return "project/project_detail";
    }
}
