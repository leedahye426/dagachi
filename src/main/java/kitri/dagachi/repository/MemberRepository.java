package kitri.dagachi.repository;

import kitri.dagachi.model.Member;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

    public Optional<Member> findByLoginInfo(String email) {
        return this.findAll().stream()
                .filter(m -> m.getEmail().equals(email))
                .findFirst();
    }

    public List<Member> findAll() {
        return em.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
    }



//    public String findByEmails(String email) {
//        return String.valueOf(em.createQuery("select m.email from Member m where m.email= :email")
//                .setParameter("email", email)
//                .getSingleResult());
//    }

//    public String findByPasswd(String email) {
//        return String.valueOf(em.createQuery("SELECT m.password FROM Member m WHERE m.email= :email")
//                .setParameter("email", email)
//                .getSingleResult());
//    }
//
//    public Long findByCode(String email) {
//
//        return Long.parseLong(String.valueOf(em.createQuery("SELECT m.code FROM Member m WHERE m.email= :email")
//                .setParameter("email", email)
//                .getSingleResult()));
//    }

    public List<Member> findByProjectId(Long project_id) {
        return em.createQuery("select m from Member m where m.id in (select p.member_id from project_members p where p.project_id= :project_id)")
                .setParameter("project_id", project_id)
                .getResultList();
    }

}
