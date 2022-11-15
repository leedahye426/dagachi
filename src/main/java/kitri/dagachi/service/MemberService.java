package kitri.dagachi.service;

//import kitri.dagachi.SecurityConfig;
import kitri.dagachi.model.Member;
import kitri.dagachi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public Long join(Member member) {

        memberRepository.save(member);
        System.out.println("member : " + member);

        return member.getId();
    }



    // 로그인 매칭V1
//    public boolean loginMatch(String email, String passwd, Long code) {
//
//        if(!email.equals(memberRepository.findByEmails(email))) {
//            System.out.println("이메일 매칭시작");
//            System.out.println("memberRepository.findByEmails(email) : " + memberRepository.findByEmails(email));
//            return false;
//        }
//        else if(!bCryptPasswordEncoder.matches(passwd, memberRepository.findByPasswd(email))) {
//            System.out.println("패스워드 매칭시작");
//            System.out.println("memberRepository.findByPasswd(email) : " + memberRepository.findByPasswd(email));
//            return false;
//        }
//        else if(!code.equals(memberRepository.findByCode(email))) {
//            System.out.println("코드 매칭시작");
//            System.out.println("memberRepository.findByCode(email) : " + memberRepository.findByCode(email));
//            return false;
//        }
//
//        System.out.println("전부 매칭 완료");
//
//        return true;
//    }

    // 로그인 매칭 V2
    public Member loginService(String email, String passwd, Long code) {
        return memberRepository.findByLoginInfo(email)
                .filter(m -> bCryptPasswordEncoder.matches(passwd, m.getPassword()))
                .filter(m -> m.getCode().equals(code))
                .orElse(null);
    }


    public List<Member> findmembers(Long project_id) {
        return memberRepository.findByProjectId(project_id);
    }

<<<<<<< HEAD

=======
>>>>>>> cf69029eabe562ec5143dfe0f06bea5586055a97
    public Member findOneByEmail(String email) {
        return memberRepository.findByEmail(email);
    }


    // 비밀번호 패턴 검증

    // 로그인 매칭
//    public boolean loginMatch(String email, String encPwd) {
//
//
//
//        if(!email.equals(memberRepository.findEmail(email))) {
//            return false;
//        }
//        else if(!encPwd.equals(memberRepository.findPasswd(encPwd))) {
//            return false;
//        }
//    }

}
