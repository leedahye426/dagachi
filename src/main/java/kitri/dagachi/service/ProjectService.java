package kitri.dagachi.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import kitri.dagachi.model.Project;
import kitri.dagachi.repository.ProjectRepository;
import lombok.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    private String fileDir="D:/test/";

    public Long register(MultipartFile file, String team_name, String project_title ) throws IOException {
        if (file.isEmpty()) {
            return null;
        }

        // 원래 파일 이름 추출
        String org_name = file.getOriginalFilename();

        // 파일 이름으로 쓸 uuid 생성
//        String uuid = UUID.randomUUID().toString();
        String uuid = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd_HHmmss_")).toString();

        // 확장자 추출(ex : .png)
//        String extension = org_name.substring(org_name.lastIndexOf("."));

        // uuid와 확장자 결합
        String saved_name = uuid + org_name;

        // 파일을 불러올 때 사용할 파일 경로
        String saved_path = fileDir + saved_name;

        // 파일 엔티티 생성
        Project project = new Project();
        project.setProject_title(project_title);
        project.setTeam_name(team_name);
        project.setOrg_name(org_name);
        project.setSaved_name(saved_name);
        project.setSaved_path(saved_path);


        // 실제로 로컬에 uuid를 파일명으로 저장
        file.transferTo(new File(saved_path));

        // 데이터베이스에 파일 정보 저장
        projectRepository.save(project);

        return project.getProject_id();
    }
    public List<Project> findProjects() {
        return projectRepository.findAll();
    }

    public Project findProject(Long project_id) {
        return projectRepository.findOne(project_id);
    }
}
