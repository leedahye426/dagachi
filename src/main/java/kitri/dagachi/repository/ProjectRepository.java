package kitri.dagachi.repository;

import java.util.ArrayList;
import java.util.List;
import kitri.dagachi.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProjectRepository {

//    private final DataSource dataSource;
//
//    public ProjectRepository(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
    private final EntityManager em;

    public void save(Project project) {
        em.persist(project);
    }

    public List<Project> findAll() {
        return em.createQuery("select p from project_board p", Project.class)
                .getResultList();
    }
}
