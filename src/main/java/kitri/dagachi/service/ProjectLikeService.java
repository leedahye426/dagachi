package kitri.dagachi.service;

import kitri.dagachi.model.ProjectLike;
import kitri.dagachi.repository.MemberRepository;
import kitri.dagachi.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectLikeService {
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    public void save(ProjectLike projectLike) {
        projectRepository.saveLike(projectLike);
    }

    public Long findLikeCnt(Long project_id, Long member_id) {
        return projectRepository.findLikeCntById(project_id, member_id);
    }


    public ProjectLike findLike(Long project_id, Long member_id) {
        return projectRepository.findLikeById(project_id, member_id);
    }

    public void deleteProjectLike(ProjectLike projectLike) {
        projectRepository.deleteLike(projectLike);
    }
}
