package kitri.dagachi.repository;

import kitri.dagachi.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class ResumeRepository {

    private final EntityManager em;

    // 개인프로필 DB 저장
    public void savePersonalInfo(PersonalInfo personalInfo) {

        System.out.println("repositorySave");
        em.persist(personalInfo);
        em.flush();
    }

    public PersonalInfo findById(Long id) {
        return em.find(PersonalInfo.class, id);
    }

    public void deletePersonalInfo(PersonalInfo personalInfo) {
        em.remove(personalInfo);
    }

    // 학력사항 DB 저장
    public void saveMemberEducation(MemberEducation memberEducation) {
        em.persist(memberEducation);
        em.flush();
        em.clear();
    }

    public List<MemberEducation> findAllEducationById(Long id) {
        return em.createQuery("SELECT m FROM MemberEducation m WHERE m.id = :id", MemberEducation.class)
                .setParameter("id", id)
                .getResultList();
    }

    public void deleteAllEducationById(Long id) {
        em.createQuery("DELETE FROM MemberEducation m WHERE m.id = :id")
                .setParameter("id", id);
    }

    // 자격증 DB 저장
    public void saveMemberCertificates(MemberCertificates memberCertificates) {
        em.persist(memberCertificates);
        em.flush();
        em.clear();
    }

    public List<MemberCertificates> findAllCertificateById(Long id) {
        return em.createQuery("SELECT m FROM MemberCertificates m WHERE m.id = :id", MemberCertificates.class)
                .setParameter("id", id)
                .getResultList();
    }

    public void deleteAllCertificateById(Long id) {
        em.createQuery("DELETE FROM MemberCertificates m WHERE m.id = :id")
                .setParameter("id", id);
    }

    // 수상경력 DB 저장
    public void saveMemberAwards(MemberAwards memberAwards) {
        em.persist(memberAwards);
        em.flush();
        em.clear();
    }

    public List<MemberAwards> findAllAwardById(Long id) {
        return em.createQuery("SELECT m FROM MemberAwards m WHERE m.id = :id", MemberAwards.class)
                .setParameter("id", id)
                .getResultList();
    }

    public void deleteAllAwardById(Long id) {
        em.createQuery("DELETE FROM MemberAwards m WHERE m.id = :id")
                .setParameter("id", id);
    }

    // 경력사항 DB 저장
    public void saveMemberCareer(MemberCareers memberCareers) {
        em.persist(memberCareers);
        em.flush();
        em.clear();
    }

    public List<MemberCareers> findAllCareerById(Long id) {
        return em.createQuery("SELECT m FROM MemberCareers m WHERE m.id = :id", MemberCareers.class)
                .setParameter("id", id)
                .getResultList();
    }

    public void deleteAllCareerById(Long id) {
        em.createQuery("DELETE FROM MemberCareers m WHERE m.id = :id")
                .setParameter("id", id);
    }
}
