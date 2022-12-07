package kitri.dagachi.repository;

import kitri.dagachi.model.Member;
import kitri.dagachi.model.PersonalInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

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

    public String DuplicateChkByEmail(String email) {
        try {
            Member member = (Member) em.createQuery("SELECT m FROM Member m WHERE m.email = :email")
                    .setParameter("email", email)
                    .getSingleResult();
            System.out.println("member.getEmail() : " + member.getEmail());
            System.out.println("true");
            if(!member.getEmail().equals("")) {
                return member.getEmail();
            }
        }
        catch (NoResultException e) {
            System.out.println("NoResultException 예외 발생");
            System.out.println("false");
//            return null;
        }
        return "";
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


    public Optional<PersonalInfo> findInfo(Long memberId) {
        List<PersonalInfo> personalInfo = em.createQuery("select pi from PersonalInfo pi where pi.id = :memberId")
                .setParameter("memberId", memberId)
                .getResultList();

        return personalInfo.stream().findAny();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    @Transactional
    @Modifying
    public void updatePasswordByEmail(String email, String newPassword) {
        em.createQuery("UPDATE Member m SET m.password = :newPassword WHERE m.email = :email")
                .setParameter("newPassword", newPassword)
                .setParameter("email", email)
                .executeUpdate();
        em.clear();
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



    public List<Member> findByName(Long memberId) {
        return em.createQuery("select m from Member m where m.id = :memberId")
                .setParameter("memberId", memberId)
                .getResultList();

    }
}
