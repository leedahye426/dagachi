package kitri.dagachi.controller;

import kitri.dagachi.model.Member;
import kitri.dagachi.model.Project;
import kitri.dagachi.model.ProjectTag;
import kitri.dagachi.service.MemberService;
import kitri.dagachi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ApprovalController {

    private final ProjectService projectService;
    private final MemberService memberService;

    @GetMapping("/project/admin/project_approve_list")
    public String projectApr(Model model) {
        List<Project> projects = projectService.findAllProjects();
        model.addAttribute("projects", projects);
        return "project/project_approve_list";
    }

    @GetMapping("/project/admin/project_approve_detail/{project_id}")
    public String detail(Model model, @PathVariable Long project_id) {
        Project project = projectService.findProject(project_id);
        List<Member> project_members = memberService.findmembers(project_id);
        List<ProjectTag> project_tags = projectService.findTags(project_id);
        model.addAttribute("project", project);
        model.addAttribute("project_members", project_members);
        model.addAttribute("project_tags", project_tags);

        return "project/project_approve_detail";
    }

    @GetMapping("/project/admin/project_approve/{project_id}")
    public String approve(@PathVariable Long project_id, Model model) {
        projectService.updateApproveYtoN(project_id);
        List<Project> projects = projectService.findAllProjects();
        model.addAttribute("projects", projects);
        return "redirect:/project/admin/project_approve_list";
    }

    @GetMapping("/project/admin/project_approve_cancel/{project_id}")
    public String approveCancel(@PathVariable Long project_id, Model model) {
        projectService.updateApproveNtoY(project_id);
        List<Project> projects = projectService.findAllProjects();
        model.addAttribute("projects", projects);
        return "redirect:/project/admin/project_approve_list";
    }
}
