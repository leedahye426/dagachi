package kitri.dagachi.repository;

import kitri.dagachi.model.Member;
import kitri.dagachi.model.PersonalInfo;
import lombok.RequiredArgsConstructor;
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

    public Optional<Member> findByLoginInfo(String email) {
        System.out.println("로그인을 위한 이메일 조회");
        return this.findAll().stream()
                .filter(m -> m.getEmail().equals(email))
                .findFirst();
    }

    public List<Member> findAll() {
        return em.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByProjectId(Long project_id) {
        return em.createQuery("select m from Member m where m.id in (select p.member_id from project_members p where p.project_id= :project_id)")
                .setParameter("project_id", project_id)
                .getResultList();
    }


    public PersonalInfo findInfo(Long memberId) {
        return (PersonalInfo) em.createQuery("select pi from PersonalInfo pi where pi.id = :memberId")
                .setParameter("memberId", memberId)
                .getSingleResult();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

//    public Member findById(Long id) {
//        return em.find(Member.class, id);
//    }

//    public void updateById(Long id, String addr, String addrDetail) {
//        em.createQuery("UPDATE Member m SET m.addr = :addr, m.addrDetail = :addrDetail WHERE m.id = :id")
//                .setParameter("addr", addr)
//                .setParameter("addrDetail", addrDetail)
//                .setParameter("id", id);
//        em.clear();
//    }

}
