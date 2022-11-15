package kitri.dagachi.repository;

import java.util.*;

import kitri.dagachi.model.Project;
import kitri.dagachi.model.ProjectMember;
import kitri.dagachi.model.ProjectTag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import javax.swing.text.html.parser.Entity;

@Repository
@RequiredArgsConstructor
public class ProjectRepository {

    private final EntityManager em;

    public void save(Project project) {
        System.out.println(project);
        em.persist(project);
    }

    public void saveProjectMember(ProjectMember projectMember) {
//        transaction.commit();
        em.persist(projectMember);
    }
    public List<Project> findAll() {
        return em.createQuery("select p from project_board p", Project.class)
                .getResultList();
    }

    public List<Project> findByTitle(String keyword) {
        return em.createQuery("select p from project_board p where p.project_title LIKE lower('%'||:keyword||'%')")
                .setParameter("keyword", keyword)
                .getResultList();
    }

    public List<Project> findByTitleTag(String keyword, String[] tags) {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        if(tags != null) {
            return em.createQuery("select p from project_board p where p.project_title Like lower('%'||:keyword||'%') and p.project_id in (select pt.project_id from project_tag pt where pt.project_tag in :tags)")
                    .setParameter("tags", Arrays.asList(tags))
                    .setParameter("keyword", keyword)
                    .getResultList();
        }
        else return em.createQuery("select p from project_board p where p.project_title LIKE lower('%'||:keyword||'%')")
                .setParameter("keyword",keyword)
                .getResultList();
    }

    public List<Project> findByTag(String[] tags) {
        return em.createQuery("select p from project_tag p where p.project_tag in :tags")
                .setParameter("tags", tags)
                .getResultList();
    }
    public Project findOne(Long project_id) {
        return em.find(Project.class, project_id);
    }

    public void deleteById(Long project_id) {
        Project project = findOne(project_id);
        em.remove(project);
    }

    public List<ProjectTag> findTagById(Long project_id) {
        return em.createQuery("select pt from project_tag pt where pt.project_id = :project_id")
                .setParameter("project_id", project_id)
                .getResultList();
    }

    public void saveProjectTag(ProjectTag projectTag) {
        em.persist(projectTag);
        em.flush();
    }

}
