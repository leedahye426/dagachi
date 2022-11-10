package kitri.dagachi.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import kitri.dagachi.model.Member;
import kitri.dagachi.model.Project;
import kitri.dagachi.model.ProjectMember;
import kitri.dagachi.repository.MemberRepository;
import kitri.dagachi.repository.ProjectRepository;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    public ProjectService(ProjectRepository projectRepository, MemberRepository memberRepository) {
        this.projectRepository = projectRepository;
        this.memberRepository = memberRepository;
    }


    private String fileDir="D:/test/";

    @Transactional
    public Long register(MultipartFile file, String team_name, String project_title, String project_content, String[] members_email) throws IOException {
        if (file.isEmpty()) {
            return null;
        }
        LocalDateTime upload_date = LocalDateTime.now();
        String org_name = file.getOriginalFilename();
        String uuid = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd_HHmmss_")).toString();
        String saved_name = uuid + org_name;
        String saved_path = fileDir + saved_name;

        // project 엔티티 생성
        Project project = new Project();
        project.setProject_title(project_title);
        project.setTeam_name(team_name);
        project.setOrg_name(org_name);
        project.setSaved_name(saved_name);
        project.setSaved_path(saved_path);
        project.setUpload_date(upload_date);
        project.setProject_content(project_content);

        // 실제로 로컬에 파일명으로 저장
        file.transferTo(new File(saved_path));

        List<Member> team_members = new ArrayList<>();
        for(String member_email : members_email) {
            team_members.add(memberRepository.findEmail(member_email));
        }

        // 데이터베이스에 정보 저장
        projectRepository.save(project);

        for(Member team_member : team_members) {
            ProjectMember projectMember = new ProjectMember();
            projectMember.setProject_id(project.getProject_id());
            projectMember.setMember_id(team_member.getId());

            projectRepository.saveProjectMember(projectMember);
        }

        return project.getProject_id();
    }
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public Project findProject(Long project_id) {
        return projectRepository.findOne(project_id);
    }
}
