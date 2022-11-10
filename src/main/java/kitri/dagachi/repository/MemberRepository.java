package kitri.dagachi.repository;

import kitri.dagachi.model.Member;
import lombok.RequiredArgsConstructor;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findByEmail(String email) {
        return (Member) em.createQuery("select m from Member m where m.email= :email ")
                .setParameter("email", email)
                .getSingleResult();
    }
    public Member findPasswd(String passwd) {
        return em.find(Member.class, passwd);
    }

    public Member findCode(int code) {
        return em.find(Member.class, code);
    }

    public List<Member> findByProjectId(Long project_id) {
        return em.createQuery("select m from Member m where m.id in (select p.member_id from project_members p where p.project_id= :project_id)")
                .setParameter("project_id", project_id)
                .getResultList();
    }

}
