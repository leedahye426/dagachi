package kitri.dagachi.repository;

import kitri.dagachi.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findEmail(String email) {
        return em.find(Member.class, email);
    }

    public Member findPasswd(String passwd) {
        return em.find(Member.class, passwd);
    }

    public Member findCode(int code) {
        return em.find(Member.class, code);
    }

}
