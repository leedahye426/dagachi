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
        return em.createQuery("select c from competition_board c", Competition.class)
                .getResultList();
    }

    public Competition findById(Long id) {
        return em.find(Competition.class, id);
    }

    public void save(Competition competition) {
        em.persist(competition);
    }

    public void delete(Competition competition) {
        em.remove(competition);
    }

    public List<Competition> findByKeyword(String keyword) {
        return em.createQuery("select c from competition_board c where c.title LIKE lower('%'||:keyword||'%')")
                .setParameter("keyword", keyword)
                .getResultList();
    }

    public List<Competition> findByMid(Long memberId) {
        return em.createQuery("select c from competition_board c where c.memberId = :memberId")
                .setParameter("memberId", memberId)
                .getResultList();
    }

    
}
