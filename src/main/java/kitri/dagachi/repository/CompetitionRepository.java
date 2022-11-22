package kitri.dagachi.repository;

import kitri.dagachi.model.Competition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.*;
@Repository
@RequiredArgsConstructor
public class CompetitionRepository {

    private final EntityManager em;


    public List<Competition> findAll() {
        return em.createQuery("select c from competition c", Competition.class)
                .getResultList();
    }
}
