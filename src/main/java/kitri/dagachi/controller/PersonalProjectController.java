package kitri.dagachi.controller;

import kitri.dagachi.model.Member;
import kitri.dagachi.model.Project;
import kitri.dagachi.model.ProjectTag;
import kitri.dagachi.service.MemberService;
import kitri.dagachi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

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
        System.out.println("**********email*********");
        for(String email: members_email) System.out.println(email);

        String[] tags = multiReq.getParameterValues("tag");
        for(String tag : tags) System.out.println(tag);
        projectService.register(file, team_name, project_title, project_content, members_email, tags);

        return "redirect:/project/personal_project_list";


    }

    @GetMapping("/project/personal/{project_id}/detail")
    public String detailPage(@PathVariable("project_id") Long project_id, Model model){
        Project project = projectService.findProject(project_id);
        List<Member> project_members = memberService.findmembers(project_id);
        List<ProjectTag> project_tags = projectService.findTags(project_id);
        model.addAttribute("project", project);
        model.addAttribute("project_members", project_members);
        model.addAttribute("project_tags", project_tags);
        return "project/project_detail";
    }

    @GetMapping("/project/search")
    public String search(@RequestParam String keyword, String[] tag, Model model) {
        List<Project> projects = projectService.findProjectsByKeywordTag(keyword,tag);
        //for(String t:tag) System.out.println(t);
        model.addAttribute("projects", projects);
        return "project/personal_project_list";
    }

    @GetMapping("/project/personal/{project_id}/delete")
    public String delete(@PathVariable Long project_id) {
        projectService.deleteProject(project_id);
        return "redirect:/project/personal_project_list";
    }

    @PostMapping("/project/email_check")
    @ResponseBody
    public ResponseEntity<?> emailCheck(String email) {
        System.out.println("--------------------------");
        System.out.println(email);


        String checkedEmail = memberService.findOneByEmail(email).getEmail();
        if(checkedEmail.equals(email)) return new ResponseEntity<>(checkedEmail, HttpStatus.OK);
        else return new ResponseEntity<>("등록된 이메일이 아닙니다.", HttpStatus.SERVICE_UNAVAILABLE);
    }

}