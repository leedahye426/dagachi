package kitri.dagachi.service;

import java.util.List;
import java.util.Optional;

import kitri.dagachi.model.Project;
import kitri.dagachi.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    public Long register(Project project) {
        projectRepository.save(project);
        return project.getProject_id();
    }

    public List<Project> findProjects() {
        return projectRepository.findAll();
    }


}
