package kitri.dagachi.service;

import kitri.dagachi.model.*;
import kitri.dagachi.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;

    public void saveInfo(PersonalInfo info) {
        System.out.println("saveinfo");
        resumeRepository.savePersonalInfo(info);
    }

    public void deleteInfo(Long id) {
        PersonalInfo personalInfo = resumeRepository.findById(id);
        resumeRepository.deletePersonalInfo(personalInfo);
    }

    public void saveEducation(MemberEducation education) {
        resumeRepository.saveMemberEducation(education);
    }

    public List<MemberEducation> findAllEducation(Long id) {
        return resumeRepository.findAllEducationById(id);
    }

    public void saveCertificates(MemberCertificates certificates) {
        resumeRepository.saveMemberCertificates(certificates);
    }

    public List<MemberCertificates> findAllCertificate(Long id) {
        return resumeRepository.findAllCertificateById(id);
    }

    public void saveAwards(MemberAwards awards) {
        resumeRepository.saveMemberAwards(awards);
    }

    public List<MemberAwards> findAllAward(Long id) {
        return resumeRepository.findAllAwardById(id);
    }

    public void saveCareer(MemberCareers career) {
        resumeRepository.saveMemberCareer(career);
    }

    public List<MemberCareers> findAllCareer(Long id) {
        return resumeRepository.findAllCareerById(id);
    }

}
