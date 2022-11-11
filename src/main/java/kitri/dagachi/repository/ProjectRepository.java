package kitri.dagachi.repository;

import java.util.ArrayList;
import java.util.List;
import kitri.dagachi.model.Project;
import kitri.dagachi.model.ProjectMember;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProjectRepository {

//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("emf");
//    private final EntityManager em = emf.createEntityManager();

    private final EntityManager em;
//    EntityTransaction transaction = em.getTransaction();

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
        return em.createQuery("select p from project_board p where p.project_title LIKE '%'||:keyword||'%'")
                .setParameter("keyword", keyword)
                .getResultList();
    }
    public Project findOne(Long project_id) {
        return em.find(Project.class, project_id);
    }

    public void deleteById(Long project_id) {
        Project project = findOne(project_id);
        em.remove(project);
    }
}
